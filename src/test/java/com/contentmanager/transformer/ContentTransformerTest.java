package com.contentmanager.transformer;

import com.contentmanager.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ContentTransformer.
 */
class ContentTransformerTest {

    private ContentTransformer contentTransformer;

    @BeforeEach
    void setUp() {
        contentTransformer = new ContentTransformer();
    }

    @Test
    void testTransformValidContent() {
        Content content = new Content();
        content.setBody("test content");

        Content transformedContent = contentTransformer.transform(content);

        assertNotNull(transformedContent);
        assertEquals("TEST CONTENT", transformedContent.getBody());
        assertEquals("Transformed Content", transformedContent.getTitle());
        assertEquals("TRANSFORMED", transformedContent.getType());
    }

    @Test
    void testTransformNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            contentTransformer.transform(null);
        });
    }

    @Test
    void testTransformToFormat() {
        Content content = new Content();
        content.setBody("test content");

        Content transformedContent = contentTransformer.transformToFormat(content, "JSON");

        assertNotNull(transformedContent);
        assertEquals("JSON", transformedContent.getType());
    }

    @Test
    void testTransformToFormatWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            contentTransformer.transformToFormat(null, "JSON");
        });
    }
}
