package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.ArtistDto;
import com.example.AppMusic.IService.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private IArtistService iArtistService;

    @PostMapping("/create")
    public ArtistDto createArtist(@RequestBody ArtistDto artistDto) {
        return iArtistService.saveArtist(artistDto);
    }

    @GetMapping
    public List<ArtistDto> getAllArtists() {
        return iArtistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ArtistDto getArtistById(@PathVariable Long id) {
        return iArtistService.getArtistById(id);
    }

    @PutMapping("/{id}")
    public ArtistDto updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDto) {
        return iArtistService.updateArtist(id, artistDto);
    }

    @SuppressWarnings("SpellCheckingInspection")
    @DeleteMapping("/{id}")
    public String deleteArtist(@PathVariable Long id) {
        iArtistService.deleteArtist(id);
        return "The artist is successfully deactivated (is_actv = 0).";
    }
}
