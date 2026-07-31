package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.PlaylistDto;

import com.example.AppMusic.IService.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private IPlaylistService playlistService;

    @PostMapping("/create")
    public PlaylistDto createPlaylist(@RequestBody PlaylistDto playlistDto) {
        return playlistService.createPlaylist(playlistDto);
    }

    @PostMapping("/{playlistId}/add-song/{songId}")
    public PlaylistDto addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        return playlistService.addSongToPlaylist(playlistId, songId);
    }

    @GetMapping("/user/{userId}")
    public List<PlaylistDto> getUserPlaylists(@PathVariable Long userId) {
        return playlistService.getUserPlaylists(userId);
    }
}
