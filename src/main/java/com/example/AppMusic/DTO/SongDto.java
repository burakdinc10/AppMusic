package com.example.AppMusic.DTO;

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
    private Long categoryId;
    private Long artistId;

    public String getsongName() {
        return null;
    }
}