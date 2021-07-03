package com.bs23test.socialmediademo.service;

import com.bs23test.socialmediademo.dto.UserDto;
import com.bs23test.socialmediademo.mapper.UserMapper;
import com.bs23test.socialmediademo.model.User;
import com.bs23test.socialmediademo.payload.ApiResponse;
import com.bs23test.socialmediademo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }



    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserDto create(UserDto userDto) {
        log.info("Request to save Users : {}", userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto update(UserDto userDto) {
        log.info("Request to update Users : {}", userDto);
        User user = userRepository.findById(userDto.getId()).get();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<User> getAll() {
        try {
            List<User> userList = userRepository.findAll();
            return userList;

        } catch (Exception ex) {
            log.error("Error occurred while getting user list: " + ex, ex);
            return null;
        }
    }

    public User findByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User findByUserID(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
