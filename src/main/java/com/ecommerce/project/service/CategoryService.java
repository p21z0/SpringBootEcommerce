package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

//    List<Category> getAllCategories();
    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO getCategory(Long categoryId);


//    void createCategory(Category category);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);


}
