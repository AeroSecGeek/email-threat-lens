package com.aerosecgeek.emailthreatlensservice.modules.analysis.body;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkExtractor {
    private static final Pattern URL_PATTERN = Pattern.compile(
            "\\b(?:https?|ftp|ftps)://[^\\s/$.?#].[^\\s]*",
            Pattern.CASE_INSENSITIVE
    );

    private LinkExtractor() {
    }

    public static List<String> extractLinks(String body, boolean isHtml) {
        List<String> links = new ArrayList<>();

        if (isHtml) {
            // Parse HTML and extract links
            Document doc = Jsoup.parse(body);
            Elements anchorTags = doc.select("a[href]");
            for (Element element : anchorTags) {
                if (element.attr("href").startsWith("mailto:")) {
                    continue;
                }
                links.add(element.attr("href"));
            }
        } else {
            // Extract links using regex for plain text
            Matcher matcher = URL_PATTERN.matcher(body);
            while (matcher.find()) {
                String link = matcher.group();
                if (link.startsWith("[") || link.startsWith("<")) {
                    link = link.substring(1);
                }
                if (link.endsWith("]") || link.endsWith(">")|| link.endsWith("\"")) {
                    link = link.substring(0, link.length() - 1);
                }
                if(link.contains("\">")){
                    link = link.substring(0, link.indexOf("\">"));
                }
                links.add(link);
            }
        }
        return links;
    }

    public static boolean isBodyHtml(String body) {
        return body.contains("<html") || body.contains("<body>")
                || body.contains("<head>")
                || body.contains("<!DOCTYPE html");
    }
}
