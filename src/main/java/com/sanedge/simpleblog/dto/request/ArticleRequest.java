package com.sanedge.simpleblog.dto.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String body;
    private Long categoryId;
    private Long tagId;
    private Date createdAt;
    private Date updatedAt;
}
