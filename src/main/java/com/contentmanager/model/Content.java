package com.contentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing content data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    
    private String id;
    private String title;
    private String body;
    private String type;
    private Long timestamp;
}
