package com.example.AppMusic.DTO;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long Id;
    private String email;
    private String username;
    private String userLastName;
    private String birthDate;
    private String nationalId;
}