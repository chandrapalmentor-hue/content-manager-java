package com.contentmanager.app.service;

import com.contentmanager.app.model.Content;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing content operations.
 * Provides business logic for content creation, retrieval, update, and deletion.
 */
@Service
public class ContentService {

    private final List<Content> contentStore = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * Retrieve all content.
     *
     * @return List of all content
     */
    public List<Content> getAllContent() {
        return new ArrayList<>(contentStore);
    }

    /**
     * Retrieve content by ID.
     *
     * @param id Content ID
     * @return Optional containing the content if found
     */
    public Optional<Content> getContentById(Long id) {
        return contentStore.stream()
                .filter(content -> content.getId().equals(id))
                .findFirst();
    }

    /**
     * Create new content.
     *
     * @param content Content to create
     * @return Created content with generated ID
     */
    public Content createContent(Content content) {
        content.setId(idCounter.getAndIncrement());
        contentStore.add(content);
        return content;
    }

    /**
     * Update existing content.
     *
     * @param id      Content ID
     * @param content Updated content data
     * @return Optional containing updated content if found
     */
    public Optional<Content> updateContent(Long id, Content content) {
        Optional<Content> existingContent = getContentById(id);
        if (existingContent.isPresent()) {
            Content updated = existingContent.get();
            updated.setTitle(content.getTitle());
            updated.setDescription(content.getDescription());
            updated.setType(content.getType());
            updated.setData(content.getData());
            return Optional.of(updated);
        }
        return Optional.empty();
    }

    /**
     * Delete content by ID.
     *
     * @param id Content ID
     * @return true if content was deleted, false otherwise
     */
    public boolean deleteContent(Long id) {
        return contentStore.removeIf(content -> content.getId().equals(id));
    }

    /**
     * Parse and transform content data.
     *
     * @param data Raw data to parse
     * @return Transformed data
     */
    public String parseAndTransform(String data) {
        // Simple example: convert to uppercase and trim
        return data != null ? data.trim().toUpperCase() : "";
    }
}
