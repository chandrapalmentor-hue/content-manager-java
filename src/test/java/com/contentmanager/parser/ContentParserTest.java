package com.contentmanager.parser;

import com.contentmanager.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ContentParser.
 */
class ContentParserTest {

    private ContentParser contentParser;

    @BeforeEach
    void setUp() {
        contentParser = new ContentParser();
    }

    @Test
    void testParseValidContent() {
        String rawContent = "This is test content";
        Content content = contentParser.parse(rawContent);

        assertNotNull(content);
        assertNotNull(content.getId());
        assertEquals(rawContent, content.getBody());
        assertNotNull(content.getTimestamp());
    }

    @Test
    void testParseNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            contentParser.parse(null);
        });
    }

    @Test
    void testParseEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            contentParser.parse("");
        });
    }

    @Test
    void testParseWhitespaceContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            contentParser.parse("   ");
        });
    }
}
