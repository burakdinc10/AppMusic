package com.example.AppMusic.DTO;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String email;
    private String password;
    private String username;
    private String birthDate;
    private String nationalId;
}