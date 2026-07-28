package com.example.AppMusic.Service;

import com.example.AppMusic.DTO.UserDto;
import com.example.AppMusic.Entity.UserEntity;
import com.example.AppMusic.IService.IUserService;
import com.example.AppMusic.Repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        UserEntity userEntity = convertToEntity(userDto);
        userRepository.save(userEntity);
        return "Başarılı";
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }

    private UserEntity convertToEntity(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), true);
    }
}
