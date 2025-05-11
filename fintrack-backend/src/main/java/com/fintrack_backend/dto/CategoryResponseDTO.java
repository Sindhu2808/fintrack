package com.fintrack_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryResponseDTO {
    private Long categoryId;
    private String categoryName;
}
