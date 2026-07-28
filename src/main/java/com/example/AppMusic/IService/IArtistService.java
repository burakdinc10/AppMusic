package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.ArtistDto;

import java.util.List;

public interface IArtistService {

  ArtistDto saveArtist(ArtistDto artistDto);

  List<ArtistDto> getAllArtists();

  ArtistDto getArtistById(Long id);

  ArtistDto updateArtist(Long id, ArtistDto artistDto);

  void deleteArtist(Long id);
}
