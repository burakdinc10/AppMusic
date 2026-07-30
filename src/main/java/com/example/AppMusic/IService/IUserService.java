package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.CreateUserRequestDto;
import com.example.AppMusic.DTO.LoginUserRequestDto;
import com.example.AppMusic.DTO.UpdateUserRequestDto;
import com.example.AppMusic.DTO.UserResponseDto;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    String createUser(CreateUserRequestDto requestDto);

    String login(LoginUserRequestDto loginRequestDto);

    String deleteUser(Long id);

    String updateUser(UpdateUserRequestDto requestDto);
}
