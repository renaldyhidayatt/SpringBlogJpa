package com.sanedge.simpleblog.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    @NotBlank
    private String description;
}
