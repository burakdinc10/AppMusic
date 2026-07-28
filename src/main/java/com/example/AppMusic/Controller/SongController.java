package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.SongDto;
import com.example.AppMusic.IService.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private ISongService iSongService;

    @PostMapping
    public SongDto createSong(@Valid @RequestBody SongDto songDto, @RequestParam Long categoryId) {
        return iSongService.saveSong(songDto, categoryId);
    }

    @GetMapping
    public List<SongDto> getAllSongs() {
        return iSongService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDto getSongById(@PathVariable Long id) {
        return iSongService.getSongById(id);
    }

    @GetMapping("/artist/{artistId}")
    public List<com.example.AppMusic.DTO.SongDto> getSongsByArtist(@PathVariable Long artistId) {
        return iSongService.getSongsByArtistId(artistId);
    }

    @GetMapping("/category/{categoryId}")
    public List<com.example.AppMusic.DTO.SongDto> getSongsByCategory(@PathVariable Long categoryId) {
        return iSongService.getSongsByCategoryId(categoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable Long id) {
        iSongService.deleteSong(id);
        return "The song is successfully deactivated. (is_actv = 0).";
    }
}
