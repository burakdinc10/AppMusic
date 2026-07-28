package com.example.AppMusic.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class UserDto {
    private Long Id;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String username;
    private String userLastName;


    private LocalDate birthDate;

    private String nationalId;
}