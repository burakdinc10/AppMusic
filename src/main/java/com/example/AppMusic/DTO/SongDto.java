package com.example.AppMusic.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private Long id;

    @NotBlank(message = "The song title cannot be empty.")
    @Size(min = 2, message = "The song title must be at least two characters long!")

    private String songName;
    private String songTime;
    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("artistId")
    private Long artistId;

}