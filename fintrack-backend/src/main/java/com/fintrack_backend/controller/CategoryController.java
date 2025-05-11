package com.fintrack_backend.controller;

import com.fintrack_backend.dto.AddCategoryDTO;
import com.fintrack_backend.dto.CategoryMapper;
import com.fintrack_backend.dto.CategoryResponseDTO;

import com.fintrack_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryDTO dto) {
        System.out.println("From controller: " + dto.getCategoryName());
        categoryService.save(dto);
        return ResponseEntity.ok("Category added");
    }

    @GetMapping("/all")
    public List<CategoryResponseDTO> getAll()
    {
        return categoryService.getALl()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }

}
