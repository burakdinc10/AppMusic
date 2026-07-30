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
import java.util.Optional;

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

    @Override
    public String createSong(SongDto songDto) {

        if (songDto == null || songDto.getSongName() == null || songDto.getSongName().trim().isEmpty()) {
            return "Hata: Şarkı ismi boş olamaz!";
        }

        SongEntity songEntity = dozerMapper.map(songDto, SongEntity.class);

        Optional<SongEntity> existingSong = songRepository.findBySongName(songDto.getSongName().trim());
        if (existingSong.isPresent()) {
            return "Böyle bir şarkı zaten veritabanında mevcut!";
        }

        if (songDto.getArtistId() != null) {
            ArtistEntity artist = artistRepository.findById(songDto.getArtistId())
                    .orElseThrow(() -> new RuntimeException("Artist bulunamadı!"));
            songEntity.setArtist(artist);
        }

        if (songDto.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(songDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı!"));
            songEntity.setCategory(category);
        }


        songRepository.save(songEntity);

        return "Şarkı başarıyla eklendi.";
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
