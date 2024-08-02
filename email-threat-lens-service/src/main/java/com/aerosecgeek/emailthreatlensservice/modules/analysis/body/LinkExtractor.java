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
                links.add(element.attr("href"));
            }
        } else {
            // Extract links using regex for plain text
            Matcher matcher = URL_PATTERN.matcher(body);
            while (matcher.find()) {
                links.add(matcher.group());
            }
        }

        return links;
    }
}
