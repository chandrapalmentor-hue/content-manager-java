package com.contentmanager.service;

import com.contentmanager.model.Content;
import com.contentmanager.parser.ContentParser;
import com.contentmanager.transformer.ContentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for content processing operations.
 */
@Service
public class ContentService {

    private final ContentParser parser;
    private final ContentTransformer transformer;

    @Autowired
    public ContentService(ContentParser parser, ContentTransformer transformer) {
        this.parser = parser;
        this.transformer = transformer;
    }

    /**
     * Process raw content by parsing and transforming it.
     * 
     * @param rawContent the raw content string
     * @return processed Content object
     */
    public Content processContent(String rawContent) {
        Content parsedContent = parser.parse(rawContent);
        return transformer.transform(parsedContent);
    }

    /**
     * Process and transform content to a specific format.
     * 
     * @param rawContent the raw content string
     * @param format the target format
     * @return processed Content object
     */
    public Content processContentWithFormat(String rawContent, String format) {
        Content parsedContent = parser.parse(rawContent);
        return transformer.transformToFormat(parsedContent, format);
    }
}
