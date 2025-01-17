package ru.company.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.exception.DuplicateDataException;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.user.User;
import ru.company.shareit.user.UserMapper;
import ru.company.shareit.user.dto.UserDto;
import ru.company.shareit.user.dto.UserUpdateDto;
import ru.company.shareit.user.repository.UserRepository;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> {
            log.info("пользователь с id {} не найден", id);
            return new NotFoundException("пользователь не найден");
        });

        return UserMapper.toUserDto(user);
    }

    public UserDto addUser(UserDto userDto) {
        userExistsByEmail(userDto.getEmail());

        User newUser = userRepository.addUser(UserMapper.fromUserDto(userDto)).orElseThrow(() -> {
            log.info("пользователь с именем {} не создан", userDto.getName());
            return new RuntimeException("пользователь не создан");
        });

        log.info("добавлен пользователь с id {}", newUser.getId());
        return UserMapper.toUserDto(newUser);
    }

    public UserDto updateUser(Long id, UserUpdateDto userDto) {
        UserDto user = getUserById(id);

        Optional.ofNullable(userDto.getName())
                .ifPresent(user::setName);

        Optional.ofNullable(userDto.getEmail())
                .ifPresent(email -> {
                    userExistsByEmail(userDto.getEmail());
                    user.setEmail(email);
                });

        User updatedUser = userRepository.updateUser(UserMapper.fromUserDto(user)).orElseThrow(() -> {
            log.info("пользователь с именем {} не обновлен", userDto.getName());
            return new RuntimeException("пользователь не обновлен");
        });

        log.info("обновлен пользователь с id {}", id);
        return UserMapper.toUserDto(updatedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
        log.info("удален пользователь с id {}", id);
    }

    private void userExistsByEmail(String email) {
        Set<String> emails = new HashSet<>();
        for (User existingUser : userRepository.getAllUsers().values()) {
            emails.add(existingUser.getEmail());
        }

        boolean emailExists = emails.contains(email);

        if (emailExists) {
            log.info("пользователь с электронной почтой '{}' уже существует", email);
            throw new DuplicateDataException("пользователь с такой электронной почтой уже существует");
        }
    }
}


