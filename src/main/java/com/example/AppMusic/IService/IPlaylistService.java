package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.PlaylistDto;
import java.util.List;

public interface IPlaylistService {
    PlaylistDto createPlaylist(PlaylistDto playlistDto);

    PlaylistDto addSongToPlaylist(Long playlistId, Long songId);

    List<PlaylistDto> getUserPlaylists(Long userId);
}