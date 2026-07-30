package com.example.AppMusic.Controller;

import com.example.AppMusic.DTO.CreateUserRequestDto;
import com.example.AppMusic.DTO.LoginUserRequestDto;
import com.example.AppMusic.DTO.UpdateUserRequestDto;
import com.example.AppMusic.DTO.UserResponseDto;
import com.example.AppMusic.Entity.UserEntity;
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
    @Autowired
    private UserRepository userRepository;


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

    @PutMapping("/update")
    public String updateUser(@RequestBody UpdateUserRequestDto requestDto){

        if (requestDto.getId() == null){
            return "Hata: Güncellenecek kullanıcı Id'si belirtilmelidir";
        }

        UserEntity existingUser = userRepository.findById(requestDto.getId())
                .orElseThrow(() -> new RuntimeException("Güncellenecek kullanıcı bulunamadı! ID: " + requestDto.getId()));

        if (requestDto.getEmail() != null && !requestDto.getEmail().trim().isEmpty()){
            String emailRegex =  "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!requestDto.getEmail().matches(emailRegex)){
                return "Geçerli bir mail adresi giriniz.";
            }
            existingUser.setEmail(requestDto.getEmail());
        }
        if (requestDto.getUsername() != null) existingUser.setUsername(requestDto.getUsername());
        if (requestDto.getBirthDate() != null) existingUser.setBirthDate(requestDto.getBirthDate());
        if (requestDto.getNationalId() != null) existingUser.setNationalId(requestDto.getNationalId());

        userRepository.save(existingUser);

        return "Kullanıcı bilgileri başarıyla güncellendi";
    }
}
