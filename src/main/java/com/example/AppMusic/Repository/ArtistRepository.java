package com.example.AppMusic.Repository;

import com.example.AppMusic.Entity.ArtistEntity;
import com.example.AppMusic.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findByIsActvTrue();
    Optional<ArtistEntity> findByArtistName(String artistName);
}