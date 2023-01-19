package com.sanedge.simpleblog.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

import com.sanedge.simpleblog.models.Category;
import com.sanedge.simpleblog.models.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String body;
    private List<Category> categories;
    private List<Tag> tags;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
