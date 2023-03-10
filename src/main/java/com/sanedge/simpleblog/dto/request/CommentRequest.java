package com.sanedge.simpleblog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank
    @Size(max = 100)
    private String content;

    @NotBlank
    private Long article_id;

    @NotBlank
    private Long user_id;

}
