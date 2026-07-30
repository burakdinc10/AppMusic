package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.CategoryDto;
import com.example.AppMusic.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/create")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return iCategoryService.saveCategory(categoryDto);
    }

    @GetMapping("/all")
    public List<CategoryDto> getAllCategory() {
        return iCategoryService.getAllCategory();
    }
}
