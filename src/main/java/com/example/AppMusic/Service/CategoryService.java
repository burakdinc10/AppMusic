package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.CategoryDto;
import com.example.AppMusic.Entity.CategoryEntity;
import com.example.AppMusic.IService.ICategoryService;
import com.example.AppMusic.Repository.CategoryRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper dozerMapper;

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        CategoryEntity category = dozerMapper.map(categoryDto, CategoryEntity.class);

        category.setIsActv(true);
        category.setCdate(new Date());
        category.setUdate(new Date());

        CategoryEntity savedCategory = categoryRepository.save(category);

        return dozerMapper.map(savedCategory, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findByIsActvTrue();
        List<CategoryDto> dtos = new ArrayList<>();

        for (CategoryEntity category : categories) {
            dtos.add(dozerMapper.map(category, CategoryDto.class));
        }
        return dtos;
    }
}
