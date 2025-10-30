package com.contentmanager.parser;

import com.contentmanager.model.Content;
import org.springframework.stereotype.Component;

/**
 * Parser component for parsing content data.
 */
@Component
public class ContentParser {

    /**
     * Parse raw content string into Content object.
     * 
     * @param rawContent the raw content string
     * @return parsed Content object
     */
    public Content parse(String rawContent) {
        // Simple parsing logic - can be extended based on requirements
        if (rawContent == null || rawContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        
        Content content = new Content();
        content.setId(generateId());
        content.setBody(rawContent);
        content.setTimestamp(System.currentTimeMillis());
        
        return content;
    }
    
    private String generateId() {
        return "CONTENT-" + System.currentTimeMillis();
    }
}
