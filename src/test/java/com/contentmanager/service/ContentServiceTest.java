package com.contentmanager.service;

import com.contentmanager.model.Content;
import com.contentmanager.parser.ContentParser;
import com.contentmanager.transformer.ContentTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ContentService.
 */
class ContentServiceTest {

    @Mock
    private ContentParser contentParser;

    @Mock
    private ContentTransformer contentTransformer;

    private ContentService contentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contentService = new ContentService(contentParser, contentTransformer);
    }

    @Test
    void testProcessContent() {
        String rawContent = "test content";
        Content parsedContent = new Content();
        parsedContent.setBody(rawContent);

        Content transformedContent = new Content();
        transformedContent.setBody("TEST CONTENT");
        transformedContent.setType("TRANSFORMED");

        when(contentParser.parse(rawContent)).thenReturn(parsedContent);
        when(contentTransformer.transform(parsedContent)).thenReturn(transformedContent);

        Content result = contentService.processContent(rawContent);

        assertNotNull(result);
        assertEquals("TEST CONTENT", result.getBody());
        assertEquals("TRANSFORMED", result.getType());
        verify(contentParser, times(1)).parse(rawContent);
        verify(contentTransformer, times(1)).transform(parsedContent);
    }

    @Test
    void testProcessContentWithFormat() {
        String rawContent = "test content";
        String format = "JSON";
        Content parsedContent = new Content();
        parsedContent.setBody(rawContent);

        Content transformedContent = new Content();
        transformedContent.setBody(rawContent);
        transformedContent.setType(format);

        when(contentParser.parse(rawContent)).thenReturn(parsedContent);
        when(contentTransformer.transformToFormat(parsedContent, format)).thenReturn(transformedContent);

        Content result = contentService.processContentWithFormat(rawContent, format);

        assertNotNull(result);
        assertEquals(format, result.getType());
        verify(contentParser, times(1)).parse(rawContent);
        verify(contentTransformer, times(1)).transformToFormat(parsedContent, format);
    }
}
