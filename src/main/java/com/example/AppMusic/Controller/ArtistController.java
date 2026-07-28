package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.ArtistDto;
import com.example.AppMusic.Service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping
    public ArtistDto createArtist(@RequestBody ArtistDto artistDto) {
        return artistService.saveArtist(artistDto);
    }

    @GetMapping
    public List<ArtistDto> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ArtistDto getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id);
    }

    @PutMapping("/{id}")
    public ArtistDto updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDto) {
        return artistService.updateArtist(id, artistDto);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @DeleteMapping("/{id}")
    public String deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return "The artist is successfully deactivated (is_actv = 0).";
    }
}