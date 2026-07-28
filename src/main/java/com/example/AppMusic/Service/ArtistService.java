package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.ArtistDto;
import com.example.AppMusic.Entity.ArtistEntity;
import com.example.AppMusic.Repository.ArtistRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private Mapper dozerMapper;

    public ArtistDto saveArtist(ArtistDto artistDto) {
        ArtistEntity entity = dozerMapper.map(artistDto, ArtistEntity.class);
        ArtistEntity savedEntity = artistRepository.save(entity);
        return dozerMapper.map(savedEntity, ArtistDto.class);
    }

    public List<ArtistDto> getAllArtists() {
        return artistRepository.findByIsActvTrue().stream()
                .map(artist -> dozerMapper.map(artist, ArtistDto.class))
                .toList();
    }

    public ArtistDto getArtistById(Long id) {
        ArtistEntity entity = artistRepository.findById(id)
                .filter(ArtistEntity::getIsActv)
                .orElseThrow(() -> new RuntimeException("Singer not found !"));
        return dozerMapper.map(entity, ArtistDto.class);
    }

    public ArtistDto updateArtist(Long id, ArtistDto artistDto) {
        ArtistEntity existingEntity = artistRepository.findById(id)
                .filter(ArtistEntity::getIsActv)
                .orElseThrow(() -> new RuntimeException("No singer was found to update!"));

        existingEntity.setArtistName(artistDto.getArtistName());
        existingEntity.setHometown(artistDto.getHometown());
        existingEntity.setPrice(artistDto.getPrice());

        ArtistEntity updatedEntity = artistRepository.save(existingEntity);
        return dozerMapper.map(updatedEntity, ArtistDto.class);
    }

    public void deleteArtist(Long id) {
        ArtistEntity entity = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No artist was found to be removed!"));
        entity.setIsActv(false);
        artistRepository.save(entity);
    }
}