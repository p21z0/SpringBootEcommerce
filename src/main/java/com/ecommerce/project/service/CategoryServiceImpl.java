package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
//    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository; 

    public List<Category> getAllCategories() {
//        return categories;
        return categoryRepository.findAll();
    }

    @Override
    public String getCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category= categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        return category.toString();
    }

    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
//        categories.add(category);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category= categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

//        categories.remove(category);
        categoryRepository.delete(category);
        return "Category no:" +categoryId+" deleted successfully.";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Optional<Category> optionalCategory= categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

//        Category savedCategory = categoryRepository.findById(categoryId)
//                .orElseThrow( () -> (new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")));

        if (optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());

            Category savedCategory = categoryRepository.save(existingCategory);

//            return existingCategory;
            return savedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
    }

}