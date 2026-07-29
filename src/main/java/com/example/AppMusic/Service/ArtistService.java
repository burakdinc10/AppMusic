package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.ArtistDto;
import com.example.AppMusic.Entity.ArtistEntity;
import com.example.AppMusic.IService.IArtistService;
import com.example.AppMusic.Repository.ArtistRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService implements IArtistService {

    private final ArtistRepository artistRepository;
    private final Mapper dozerMapper;

    @Override
    public ArtistDto saveArtist(ArtistDto artistDto) {
        return null;
    }

    @Override
    public String createArtist(ArtistDto artistDto) {

        if (artistDto == null || artistDto.getArtistName() == null || artistDto.getArtistName().trim().isEmpty()) {
            return "Hata: Sanatçı ismi olmadan kayıt yapılamaz!";
        }

        ArtistEntity artistEntity = dozerMapper.map(artistDto, ArtistEntity.class);
        artistRepository.save(artistEntity);

        return "Sanatçı başarıyla eklendi.";
    }

    @Override
    public List<ArtistDto> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(artist -> dozerMapper.map(artist, ArtistDto.class))
                .toList();
    }

    @Override
    public ArtistDto getArtistById(Long id) {
        return null;
    }

    @Override
    public ArtistDto updateArtist(Long id, ArtistDto artistDto) {
        return null;
    }

    @Override
    public void deleteArtist(Long id) {

    }
}