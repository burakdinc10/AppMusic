package com.example.AppMusic.IService;

import com.example.AppMusic.DTO.LoginUserRequestDto;
import com.example.AppMusic.DTO.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    String createUser(@RequestBody UserDto userDto);

    String login(LoginUserRequestDto loginRequestDto);

    String deleteUser(Long id);
}
