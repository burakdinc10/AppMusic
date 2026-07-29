package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.CreateUserRequestDto;
import com.example.AppMusic.DTO.LoginUserRequestDto;
import com.example.AppMusic.DTO.UserResponseDto;
import com.example.AppMusic.IService.IUserService;
import com.example.AppMusic.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return iUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserId(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody CreateUserRequestDto requestDto) {
        return iUserService.createUser(requestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        return iUserService.login(loginUserRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
        return "The user is successfully deactivated.";
    }
}
