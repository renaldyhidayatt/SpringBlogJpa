package com.sanedge.simpleblog.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

import com.sanedge.simpleblog.models.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private long id;
    private String name;
    private String slug;
    private String description;
    private List<Article> articles;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
