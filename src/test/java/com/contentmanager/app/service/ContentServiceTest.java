package com.contentmanager.app.service;

import com.contentmanager.app.model.Content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ContentService.
 */
class ContentServiceTest {

    private ContentService contentService;

    @BeforeEach
    void setUp() {
        contentService = new ContentService();
    }

    @Test
    void testCreateContent() {
        Content content = new Content(null, "Test Title", "Test Description", "text", "test data");
        Content created = contentService.createContent(content);

        assertNotNull(created.getId());
        assertEquals("Test Title", created.getTitle());
        assertEquals("Test Description", created.getDescription());
        assertEquals("text", created.getType());
        assertEquals("test data", created.getData());
    }

    @Test
    void testGetAllContent() {
        Content content1 = new Content(null, "Title 1", "Description 1", "text", "data 1");
        Content content2 = new Content(null, "Title 2", "Description 2", "json", "data 2");

        contentService.createContent(content1);
        contentService.createContent(content2);

        List<Content> allContent = contentService.getAllContent();
        assertEquals(2, allContent.size());
    }

    @Test
    void testGetContentById() {
        Content content = new Content(null, "Test Title", "Test Description", "text", "test data");
        Content created = contentService.createContent(content);

        Optional<Content> found = contentService.getContentById(created.getId());
        assertTrue(found.isPresent());
        assertEquals(created.getId(), found.get().getId());
        assertEquals("Test Title", found.get().getTitle());
    }

    @Test
    void testGetContentByIdNotFound() {
        Optional<Content> found = contentService.getContentById(999L);
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateContent() {
        Content content = new Content(null, "Original Title", "Original Description", "text", "original data");
        Content created = contentService.createContent(content);

        Content updateData = new Content(null, "Updated Title", "Updated Description", "json", "updated data");
        Optional<Content> updated = contentService.updateContent(created.getId(), updateData);

        assertTrue(updated.isPresent());
        assertEquals("Updated Title", updated.get().getTitle());
        assertEquals("Updated Description", updated.get().getDescription());
        assertEquals("json", updated.get().getType());
        assertEquals("updated data", updated.get().getData());
    }

    @Test
    void testUpdateContentNotFound() {
        Content updateData = new Content(null, "Updated Title", "Updated Description", "json", "updated data");
        Optional<Content> updated = contentService.updateContent(999L, updateData);

        assertFalse(updated.isPresent());
    }

    @Test
    void testDeleteContent() {
        Content content = new Content(null, "Test Title", "Test Description", "text", "test data");
        Content created = contentService.createContent(content);

        boolean deleted = contentService.deleteContent(created.getId());
        assertTrue(deleted);

        Optional<Content> found = contentService.getContentById(created.getId());
        assertFalse(found.isPresent());
    }

    @Test
    void testDeleteContentNotFound() {
        boolean deleted = contentService.deleteContent(999L);
        assertFalse(deleted);
    }

    @Test
    void testParseAndTransform() {
        String input = "  hello world  ";
        String result = contentService.parseAndTransform(input);
        assertEquals("HELLO WORLD", result);
    }

    @Test
    void testParseAndTransformNull() {
        String result = contentService.parseAndTransform(null);
        assertEquals("", result);
    }
}
