package com.fintrack_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddCategoryDTO {
    @NotBlank(message = "Category is required")
    private String categoryName;
}
