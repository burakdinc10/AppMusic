package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.SongDto;
import com.example.AppMusic.Entity.SongEntity;
import com.example.AppMusic.Entity.CategoryEntity;
import com.example.AppMusic.Entity.ArtistEntity;
import com.example.AppMusic.IService.ISongService;
import com.example.AppMusic.Repository.SongRepository;
import com.example.AppMusic.Repository.CategoryRepository;
import com.example.AppMusic.Repository.ArtistRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SongService implements ISongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private Mapper dozerMapper;

    public SongDto saveSong(SongDto songDto, Long categoryId) {
        SongEntity songEntity = dozerMapper.map(songDto, SongEntity.class);
        songEntity.setIsActv(true);

        if (categoryId != null) {
            CategoryEntity category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("The category is not found"));
            songEntity.setCategory(category);
        }

        if (songDto.getArtistId() != null) {
            ArtistEntity artist = artistRepository.findById(songDto.getArtistId())
                    .orElseThrow(() -> new RuntimeException("The artist is not found"));
            songEntity.setArtist(artist);
        }

        SongEntity savedSong = songRepository.save(songEntity);

        SongDto responseDto = dozerMapper.map(savedSong, SongDto.class);
        if (savedSong.getCategory() != null) {
            responseDto.setCategoryId(savedSong.getCategory().getId());
        }
        if (savedSong.getArtist() != null) {
            responseDto.setArtistId(savedSong.getArtist().getId());
        }
        return responseDto;
    }

    public SongDto getSongById(Long id) {
        SongEntity song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The song is not found"));

        SongDto dto = dozerMapper.map(song, SongDto.class);
        if (song.getCategory() != null) {
            dto.setCategoryId(song.getCategory().getId());
        }
        if (song.getArtist() != null) {
            dto.setArtistId(song.getArtist().getId());
        }
        return dto;
    }

    public List<SongDto> getAllSongs() {
        return songRepository.findByIsActvTrue().stream()
                .map(song -> {
                    SongDto dto = dozerMapper.map(song, SongDto.class);
                    if (song.getCategory() != null) {
                        dto.setCategoryId(song.getCategory().getId());
                    }
                    if (song.getArtist() != null) {
                        dto.setArtistId(song.getArtist().getId());
                    }
                    return dto;
                })
                .toList();
    }

    public List<SongDto> getSongsByArtistId(Long artistId) {
        return songRepository.findByArtist_Id(artistId).stream()
                .map(song -> {
                    SongDto dto = dozerMapper.map(song, SongDto.class);
                    if (song.getCategory() != null) {
                        dto.setCategoryId(song.getCategory().getId());
                    }
                    if (song.getArtist() != null) {
                        dto.setArtistId(song.getArtist().getId());
                    }
                    return dto;
                })
                .toList();
    }

    public List<SongDto> getSongsByCategoryId(Long categoryId) {
        return songRepository.findByCategory_Id(categoryId).stream()
                .map(song -> {
                    SongDto dto = dozerMapper.map(song, SongDto.class);
                    if (song.getCategory() != null) {
                        dto.setCategoryId(song.getCategory().getId());
                    }
                    if (song.getArtist() != null) {
                        dto.setArtistId(song.getArtist().getId());
                    }
                    return dto;
                })
                .toList();
    }

    public void deleteSong(Long id) {
        SongEntity song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The song you wanted to delete could not be found."));
        songRepository.delete(song);
    }
}
