package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.CategoryDto;
import com.example.AppMusic.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return iCategoryService.saveCategory(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return iCategoryService.getAllCategories();
    }
}
