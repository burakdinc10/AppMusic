package com.example.AppMusic.Repository;

import com.example.AppMusic.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByIsActvTrue();
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}