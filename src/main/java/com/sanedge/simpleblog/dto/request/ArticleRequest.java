package com.sanedge.simpleblog.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank
    @Size(min = 3, max = 50)
    private String slug;

    @NotBlank
    @Size(min = 3, max = 150)
    private String description;

    @NotBlank
    @Size(min = 3, max = 150)
    private String body;

    @NotBlank
    @Min(1)
    private Long categoryId;

    @NotBlank
    @Min(1)
    private Long tagId;

    @NotBlank
    private Date createdAt;

    @Past
    private Date updatedAt;
}
