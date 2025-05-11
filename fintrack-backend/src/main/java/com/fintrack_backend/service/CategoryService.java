package com.fintrack_backend.service;
import com.fintrack_backend.model.Category;
import com.fintrack_backend.repository.CategoryRepository;
import com.fintrack_backend.dto.AddCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    public void save(AddCategoryDTO addCategoryDTO)
    {
        try {
            Category category = new Category();
            category.setCategoryName(addCategoryDTO.getCategoryName());
            System.out.println(category.getCategoryName());
            categoryRepository.save(category);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }

    public List<Category> getALl()
    {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

}
