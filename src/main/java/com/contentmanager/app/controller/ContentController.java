package com.contentmanager.app.controller;

import com.contentmanager.app.model.Content;
import com.contentmanager.app.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for content management operations.
 * Provides endpoints for CRUD operations on content.
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
     * Get all content.
     *
     * @return List of all content
     */
    @GetMapping
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    /**
     * Get content by ID.
     *
     * @param id Content ID
     * @return Content if found, 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        Optional<Content> content = contentService.getContentById(id);
        return content.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new content.
     *
     * @param content Content to create
     * @return Created content
     */
    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        Content created = contentService.createContent(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update existing content.
     *
     * @param id      Content ID
     * @param content Updated content data
     * @return Updated content if found, 404 otherwise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content content) {
        Optional<Content> updated = contentService.updateContent(id, content);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete content by ID.
     *
     * @param id Content ID
     * @return 204 if deleted, 404 otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        boolean deleted = contentService.deleteContent(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Parse and transform data.
     *
     * @param data Raw data to parse
     * @return Transformed data
     */
    @PostMapping("/parse")
    public ResponseEntity<String> parseData(@RequestBody String data) {
        String transformed = contentService.parseAndTransform(data);
        return ResponseEntity.ok(transformed);
    }
}
