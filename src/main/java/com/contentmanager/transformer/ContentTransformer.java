package com.contentmanager.transformer;

import com.contentmanager.model.Content;
import org.springframework.stereotype.Component;

/**
 * Transformer component for transforming content data.
 */
@Component
public class ContentTransformer {

    /**
     * Transform content by applying various transformations.
     * 
     * @param content the content to transform
     * @return transformed Content object
     */
    public Content transform(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        
        // Apply transformations
        if (content.getBody() != null) {
            // Example: uppercase transformation
            content.setBody(content.getBody().toUpperCase());
        }
        
        if (content.getTitle() == null || content.getTitle().isEmpty()) {
            content.setTitle("Transformed Content");
        }
        
        content.setType("TRANSFORMED");
        
        return content;
    }
    
    /**
     * Transform content to a specific format.
     * 
     * @param content the content to transform
     * @param format the target format
     * @return transformed Content object
     */
    public Content transformToFormat(Content content, String format) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        
        content.setType(format);
        return content;
    }
}
