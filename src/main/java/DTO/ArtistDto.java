package com.example.AppMusic.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDto {
    private Long id;
    private String artistName;
    private String hometown;
    private Double price;
}