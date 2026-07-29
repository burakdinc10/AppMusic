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

    @Override
    public String createCategory(CategoryDto categoryDto) {

        if (categoryDto.getCategoryName() == null || categoryDto.getCategoryName().trim().isEmpty()) {
            return "Kategori adı boş olamaz!";
        }

        if (categoryRepository.findByCategoryName(categoryDto.getCategoryName().trim()).isPresent()) {
            return "Böyle bir kategori zaten mevcuttur .";
        }

        CategoryEntity categoryEntity = dozerMapper.map(categoryDto, CategoryEntity.class);
        categoryRepository.save(categoryEntity);

        return "Kategori başarıyla oluşturuldu.";
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> category = categoryRepository.findByIsActvTrue();
        List<CategoryDto> dtos = new ArrayList<>();

        for (CategoryEntity categories : category) {
            dtos.add(dozerMapper.map(category, CategoryDto.class));
        }
        return dtos;
    }
}
