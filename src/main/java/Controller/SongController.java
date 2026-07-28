package com.example.musicapp.Controller;

import com.example.AppMusic.DTO.SongDto;
import com.example.AppMusic.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping
    public SongDto createSong(@jakarta.validation.Valid @RequestBody SongDto songDto, @RequestParam Long categoryId) {
        return songService.saveSong(songDto, categoryId);
    }

    @GetMapping
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDto getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    @GetMapping("/artist/{artistId}")
    public List<com.example.AppMusic.DTO.SongDto> getSongsByArtist(@PathVariable Long artistId) {
        return songService.getSongsByArtistId(artistId);
    }

    @GetMapping("/category/{categoryId}")
    public List<com.example.AppMusic.DTO.SongDto> getSongsByCategory(@PathVariable Long categoryId) {
        return songService.getSongsByCategoryId(categoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "The song is successfully deactivated. (is_actv = 0).";
    }
}