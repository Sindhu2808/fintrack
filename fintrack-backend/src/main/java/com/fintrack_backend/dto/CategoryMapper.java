package com.fintrack_backend.dto;

import com.fintrack_backend.model.Category;

public class CategoryMapper {

    public static Category toEntity(AddCategoryDTO dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        return category;
    }
    public static CategoryResponseDTO toDto(Category category)
    {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        return dto;

    }
}
