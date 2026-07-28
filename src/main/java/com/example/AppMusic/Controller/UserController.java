package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.UserDto;
import com.example.AppMusic.IService.IUserService;
import com.example.AppMusic.Repository.UserRepository;
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
    public List<UserDto> getAllUsers() {
        return iUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserId(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserDto userDto) {
        return iUserService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
        return "The user is successfully deactivated.";
    }
}
