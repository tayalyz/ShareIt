package ru.company.shareit.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.exception.DuplicateDataException;
import ru.company.shareit.exception.NotFoundException;
import ru.company.shareit.user.User;
import ru.company.shareit.user.UserMapper;
import ru.company.shareit.user.dto.UserDto;
import ru.company.shareit.user.dto.UserUpdateDto;
import ru.company.shareit.user.repository.UserJpaRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto getUserById(Long id) {
        User user = userJpaRepository.findById(id).orElseThrow(() -> {
            log.info("пользователь с id {} не найден", id);
            return new NotFoundException("пользователь не найден");
        });

        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        userExistsByEmail(userDto.getEmail());
        User newUser = userJpaRepository.save(UserMapper.fromUserDto(userDto));

        log.info("добавлен пользователь с id {}", newUser.getId());
        return UserMapper.toUserDto(newUser);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateDto userDto) {
        User user = UserMapper.fromUserDto(getUserById(id));

        Optional.ofNullable(userDto.getName())
                .ifPresent(user::setName);

        Optional.ofNullable(userDto.getEmail())
                .ifPresent(email -> {
                    userExistsByEmail(userDto.getEmail());
                    user.setEmail(email);
                });

        User updatedUser = userJpaRepository.save(user);

        log.info("обновлен пользователь с id {}", id);
        return UserMapper.toUserDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
        log.info("удален пользователь с id {}", id);
    }

    private void userExistsByEmail(String email) {
        Set<String> emails = new HashSet<>();
        for (User existingUser : userJpaRepository.findAll()) {
            emails.add(existingUser.getEmail());
        }

        boolean emailExists = emails.contains(email);

        if (emailExists) {
            log.info("пользователь с электронной почтой '{}' уже существует", email);
            throw new DuplicateDataException("пользователь с такой электронной почтой уже существует");
        }
    }
}


