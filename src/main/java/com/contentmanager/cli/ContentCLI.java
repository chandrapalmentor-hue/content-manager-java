package com.contentmanager.cli;

import com.contentmanager.model.Content;
import com.contentmanager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Command Line Interface for content processing.
 * This component runs when the application starts if enabled.
 */
@Component
public class ContentCLI implements CommandLineRunner {

    private final ContentService contentService;

    @Autowired
    public ContentCLI(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public void run(String... args) throws Exception {
        // CLI is disabled by default
        // Uncomment the following lines to enable CLI processing
        
        /*
        if (args.length > 0) {
            System.out.println("Processing content via CLI...");
            String rawContent = String.join(" ", args);
            Content processedContent = contentService.processContent(rawContent);
            System.out.println("Processed Content: " + processedContent);
        } else {
            System.out.println("Content Manager CLI");
            System.out.println("Usage: java -jar content-manager.jar <content-to-process>");
        }
        */
    }
}
