package com.aerosecgeek.emailthreatlensservice.modules.analysis.body;

import com.aerosecgeek.emailthreatlensservice.modules.testdata.MessageExample;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkExtractorTest {
    @Test
    void givenPlainTextWith2Links_whenExtractLinks_thenReturn2Links() {
        // given
        String body = "This is a test message with 2 links: http://www.google.com and https://12.34.56.8";

        // when
        var result = LinkExtractor.extractLinks(body, false);

        assertEquals(2, result.size());
        assertTrue(result.contains("http://www.google.com"));
        assertTrue(result.contains("https://12.34.56.8"));
    }

    @Test
    void givenPlainTextWithoutLinks_whenExtractLinks_thenReturnEmptyList() {
        // given
        String body = "This is a test message without any links";

        // when
        var result = LinkExtractor.extractLinks(body, false);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenHtmlWith3Links_whenExtractLinks_thenReturn3Links() {
        // given
        String body = "<html><body><a href=\"http://www.google.com\">Google</a> <a href=\"https://www.yahoo.com\">Yahoo</a> <a href=\"https://www.bing.com\">Bing</a></body></html>";

        // when
        var result = LinkExtractor.extractLinks(body, true);

        // then
        assertEquals(3, result.size());
        assertTrue(result.contains("http://www.google.com"));
        assertTrue(result.contains("https://www.yahoo.com"));
        assertTrue(result.contains("https://www.bing.com"));
    }

    @Test
    void givenHtmlWithoutLinks_whenExtractLinks_thenReturnEmptyList() {
        // given
        String body = "<html><body>This is a test message without any links</body></html>";

        // when
        var result = LinkExtractor.extractLinks(body, true);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenHtmlWithMailtoLink_whenExtractLinks_thenReturnEmptyList() {
        // given
        String body = "<html><body><a href=\"mailto:test@test.com\">Email me</a></body></html>";

        // when
        var result = LinkExtractor.extractLinks(body, true);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenHtmlBlock_whenIsBodyHtml_thenReturnTrue() {
        // given
        String body = MessageExample.getHtmlContent();

        // when
        var result = LinkExtractor.isBodyHtml(body);

        // then
        assertTrue(result);
    }

}