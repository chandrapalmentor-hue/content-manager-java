package com.contentmanager.controller;

import com.contentmanager.model.Content;
import com.contentmanager.service.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
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

    @MockBean
    private ContentService contentService;

    private Content testContent;

    @BeforeEach
    void setUp() {
        testContent = new Content();
        testContent.setId("TEST-123");
        testContent.setBody("TEST CONTENT");
        testContent.setType("TRANSFORMED");
    }

    @Test
    void testProcessContent() throws Exception {
        when(contentService.processContent(anyString())).thenReturn(testContent);

        mockMvc.perform(post("/api/content/process")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test content"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("TEST-123"))
                .andExpect(jsonPath("$.body").value("TEST CONTENT"))
                .andExpect(jsonPath("$.type").value("TRANSFORMED"));
    }

    @Test
    void testProcessContentWithFormat() throws Exception {
        Content jsonContent = new Content();
        jsonContent.setId("TEST-456");
        jsonContent.setBody("test content");
        jsonContent.setType("JSON");

        when(contentService.processContentWithFormat(anyString(), anyString())).thenReturn(jsonContent);

        mockMvc.perform(post("/api/content/process/JSON")
                .contentType(MediaType.TEXT_PLAIN)
                .content("test content"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("TEST-456"))
                .andExpect(jsonPath("$.type").value("JSON"));
    }

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/content/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Content Manager Service is running"));
    }

    @Test
    void testProcessContentWithError() throws Exception {
        when(contentService.processContent(anyString()))
                .thenThrow(new IllegalArgumentException("Invalid content"));

        mockMvc.perform(post("/api/content/process")
                .contentType(MediaType.TEXT_PLAIN)
                .content(""))
                .andExpect(status().isBadRequest());
    }
}
