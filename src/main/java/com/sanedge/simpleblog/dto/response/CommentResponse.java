package com.sanedge.simpleblog.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private ArticleResponse article;
    private UserResponse user;
}
