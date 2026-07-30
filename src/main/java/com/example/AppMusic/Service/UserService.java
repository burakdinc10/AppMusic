package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.*;
import com.example.AppMusic.Entity.UserEntity;
import com.example.AppMusic.IService.IUserService;
import com.example.AppMusic.Repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper dozerMapper;


    public List<UserResponseDto> getAllUsers() {
        return userRepository.findByIsActvTrue().stream()
                .map(user -> dozerMapper.map(user, UserResponseDto.class))
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity userEntity =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user is not found! ID: " + id));

        return dozerMapper.map(userEntity, UserResponseDto.class);
    }


    public String createUser(CreateUserRequestDto requestDto){

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!requestDto.getEmail().matches(emailRegex)) {
            return "Geçerli bir mail adresi giriniz.";
        }

        if (requestDto.getEmail() == null) {
            return "Mail adresi boş olamaz.";
        }

        if (requestDto.getPassword() == null) {
            return "Password boş olamaz.";
        }

        UserEntity byEmail = userRepository.findByEmail(requestDto.getEmail());
        if (byEmail != null) {
            return "Böyle bir mail adresi var.";
        }

        UserEntity userEntity = dozerMapper.map(requestDto, UserEntity.class);
        userRepository.save(userEntity);
        return "Başarılı";
    }

    @Override
    public String login(LoginUserRequestDto loginRequestDto) {

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (loginRequestDto == null ||
                loginRequestDto.getEmail() == null ||
                !Pattern.matches(emailRegex, loginRequestDto.getEmail())) {

            return "Kardeş giremedin düzgün mail adresi gir";
        }


        Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findByEmail(loginRequestDto.getEmail()));

        if (userOptional.isEmpty()) {
            return "Girdiğiniz bilgiler yanlış.";
        }

        UserEntity user = userOptional.get();


        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            return "Girdiğiniz bilgiler yanlış.";
        }


        return "Başarıyla giriş yapabildin.";
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }

    @Override
    public String updateUser(UpdateUserRequestDto requestDto) {
        return "";
    }

    private UserEntity convertToEntity(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), true, dto.getBirthDate(), dto.getNationalId());
    }
}
