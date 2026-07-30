package com.example.AppMusic.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {

    private Long id;
    private String email;
    private String username;
    private String birthDate;
    private String nationalId;
}