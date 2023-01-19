package com.sanedge.simpleblog.dto.response;

import java.time.ZonedDateTime;
// import java.util.List;

// import com.sanedge.simpleblog.models.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}