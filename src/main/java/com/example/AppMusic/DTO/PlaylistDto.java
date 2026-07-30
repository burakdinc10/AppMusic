package com.example.AppMusic.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {

    private Long id;
    private String playlistName;

    @JsonProperty("userId")
    private Long userId;

    private Set<SongDto> songs;

}
