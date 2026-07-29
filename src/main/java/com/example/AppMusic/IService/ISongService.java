package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.SongDto;

import java.util.List;

public interface ISongService {

  String createSong(SongDto songDto);

  SongDto getSongById(Long id);

  List<SongDto> getAllSongs();

  List<SongDto> getSongsByArtistId(Long artistId);

  List<SongDto> getSongsByCategoryId(Long categoryId);

  void deleteSong(Long id);
}
