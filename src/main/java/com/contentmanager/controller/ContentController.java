package com.contentmanager.controller;

import com.contentmanager.model.Content;
import com.contentmanager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for content management operations.
 */
@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * Process content endpoint.
     * 
     * @param rawContent the raw content to process
     * @return processed content
     */
    @PostMapping("/process")
    public ResponseEntity<Content> processContent(@RequestBody String rawContent) {
        try {
            Content processedContent = contentService.processContent(rawContent);
            return ResponseEntity.ok(processedContent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Process content with specific format endpoint.
     * 
     * @param rawContent the raw content to process
     * @param format the target format
     * @return processed content
     */
    @PostMapping("/process/{format}")
    public ResponseEntity<Content> processContentWithFormat(
            @RequestBody String rawContent,
            @PathVariable String format) {
        try {
            Content processedContent = contentService.processContentWithFormat(rawContent, format);
            return ResponseEntity.ok(processedContent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Health check endpoint.
     * 
     * @return status message
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Content Manager Service is running");
    }
}
