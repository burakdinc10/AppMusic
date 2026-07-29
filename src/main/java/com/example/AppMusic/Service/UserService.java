package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.LoginUserRequestDto;
import com.example.AppMusic.DTO.UserDto;
import com.example.AppMusic.Entity.UserEntity;
import com.example.AppMusic.IService.IUserService;
import com.example.AppMusic.Repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper dozerMapper;


    public List<UserDto> getAllUsers() {
        return userRepository.findByIsActvTrue().stream()
                .map(user -> dozerMapper.map(user, UserDto.class))
                .toList();
    }

    public UserDto getUserById(Long id) {
        UserEntity entity =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user is not found! ID: " + id));
        return null;
    }


    public String createUser(UserDto userDto){

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!userDto.getEmail().matches(emailRegex)) {
            return "Geçerli bir mail adresi giriniz.";
        }

        if (userDto.getEmail() == null) {
            return "Mail adresi boş olamaz.";
        }

        if (userDto.getPassword() == null) {
            return "Password boş olamaz.";
        }

        UserEntity byEmail = userRepository.findByEmail(userDto.getEmail());
        if (byEmail != null) {
            return "Böyle bir mail adresi var.";
        }

        UserEntity userEntity = convertToEntity(userDto);
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

    private UserEntity convertToEntity(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), true, dto.getBirthDate(), dto.getNationalId());
    }
}
