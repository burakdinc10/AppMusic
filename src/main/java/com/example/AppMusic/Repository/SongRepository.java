package com.example.AppMusic.Repository;

import com.example.AppMusic.Entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findByArtist_Id(Long artistId);

    List<SongEntity> findByIsActvTrue();

    List<SongEntity> findByCategory_Id(Long categoryId);
}