package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.PlaylistDto;
import com.example.AppMusic.DTO.SongDto;
import com.example.AppMusic.Entity.PlaylistEntity;
import com.example.AppMusic.Entity.SongEntity;
import com.example.AppMusic.Entity.UserEntity;
import com.example.AppMusic.IService.IPlaylistService;
import com.example.AppMusic.Repository.PlaylistRepository;
import com.example.AppMusic.Repository.SongRepository;
import com.example.AppMusic.Repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private Mapper dozerMapper;

    @Override
    @Transactional
    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {
        if (playlistDto.getUserId() == null) {
            throw new RuntimeException("Hata: Kullanıcı ID boş olamaz!");
        }

        UserEntity user = userRepository.findById(playlistDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Hata: Kullanıcı bulunamadı!"));

        PlaylistEntity playlist = new PlaylistEntity();
        playlist.setPlaylistName(playlistDto.getPlaylistName());
        playlist.setUser(user);

        PlaylistEntity savedPlaylist = playlistRepository.save(playlist);

        PlaylistDto responseDto = dozerMapper.map(savedPlaylist, PlaylistDto.class);
        responseDto.setUserId(user.getId());
        return responseDto;
    }


    @Override
    @Transactional
    public PlaylistDto addSongToPlaylist(Long playlistId, Long songId) {
        PlaylistEntity playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Hata: Playlist bulunamadı!"));

        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Hata: Şarkı bulunamadı!"));

        playlist.getSongs().add(song);

        PlaylistEntity updatedPlaylist = playlistRepository.save(playlist);

        playlist.setSongCount(playlist.getSongs().size());

        PlaylistDto dto = dozerMapper.map(updatedPlaylist, PlaylistDto.class);
        dto.setUserId(updatedPlaylist.getUser().getId());

        Set<SongDto> songDtos = updatedPlaylist.getSongs().stream().map(s -> {
            SongDto sDto = dozerMapper.map(s, SongDto.class);
            if (s.getArtist() != null) sDto.setArtistId(s.getArtist().getId());
            if (s.getCategory() != null) sDto.setCategoryId(s.getCategory().getId());
            return sDto;
        }).collect(Collectors.toSet());

        dto.setSongs(songDtos);
        return dto;
    }

    @Override
    public List<PlaylistDto> getUserPlaylists(Long userId) {
        List<PlaylistEntity> playlists = playlistRepository.findByUserId(userId);

        return playlists.stream().map(pl -> {
            PlaylistDto dto = dozerMapper.map(pl, PlaylistDto.class);
            dto.setUserId(pl.getUser().getId());
            return dto;
        }).toList();
    }

    public PlaylistDto convertToDto(PlaylistEntity playlistEntity) {
        PlaylistDto dto = dozerMapper.map(playlistEntity, PlaylistDto.class);

        if (playlistEntity.getSongs() != null) {
            dto.setSongCount(playlistEntity.getSongs().size());
        } else {
            dto.setSongCount(0);
        }

        return dto;
    }


}
