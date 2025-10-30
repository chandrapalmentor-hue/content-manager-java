package com.contentmanager.app.model;

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
    private Long id;
    private String title;
    private String description;
    private String type;
    private String data;
}
