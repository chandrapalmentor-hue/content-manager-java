package com.contentmanager.app.controller;

import com.contentmanager.app.model.Content;
import com.contentmanager.app.service.ContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for ContentController.
 */
@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContentService contentService;

    @Test
    void testGetAllContent() throws Exception {
        List<Content> contentList = Arrays.asList(
                new Content(1L, "Title 1", "Description 1", "text", "data 1"),
                new Content(2L, "Title 2", "Description 2", "json", "data 2")
        );

        when(contentService.getAllContent()).thenReturn(contentList);

        mockMvc.perform(get("/api/content"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    void testGetContentById() throws Exception {
        Content content = new Content(1L, "Test Title", "Test Description", "text", "test data");

        when(contentService.getContentById(1L)).thenReturn(Optional.of(content));

        mockMvc.perform(get("/api/content/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testGetContentByIdNotFound() throws Exception {
        when(contentService.getContentById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/content/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateContent() throws Exception {
        Content inputContent = new Content(null, "New Title", "New Description", "text", "new data");
        Content createdContent = new Content(1L, "New Title", "New Description", "text", "new data");

        when(contentService.createContent(any(Content.class))).thenReturn(createdContent);

        mockMvc.perform(post("/api/content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputContent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    void testUpdateContent() throws Exception {
        Content updateContent = new Content(null, "Updated Title", "Updated Description", "json", "updated data");
        Content updatedContent = new Content(1L, "Updated Title", "Updated Description", "json", "updated data");

        when(contentService.updateContent(eq(1L), any(Content.class))).thenReturn(Optional.of(updatedContent));

        mockMvc.perform(put("/api/content/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateContent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testUpdateContentNotFound() throws Exception {
        Content updateContent = new Content(null, "Updated Title", "Updated Description", "json", "updated data");

        when(contentService.updateContent(eq(999L), any(Content.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/content/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateContent)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteContent() throws Exception {
        when(contentService.deleteContent(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/content/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteContentNotFound() throws Exception {
        when(contentService.deleteContent(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/content/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testParseData() throws Exception {
        when(contentService.parseAndTransform("test data")).thenReturn("TEST DATA");

        mockMvc.perform(post("/api/content/parse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("test data"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("\"TEST DATA\""));
    }
}
