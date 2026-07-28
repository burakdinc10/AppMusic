package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.CategoryDto;

import java.util.List;

public interface ICategoryService {

  CategoryDto saveCategory(CategoryDto categoryDto);

  List<CategoryDto> getAllCategories();
}
