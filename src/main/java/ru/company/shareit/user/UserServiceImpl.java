package ru.company.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.company.shareit.exception.DuplicateDataException;
import ru.company.shareit.exception.NotFoundException;

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
        userExistsByEmail(userDto.email);

        User user = UserMapper.fromUserDto(userDto);
        userRepository.addUser(user);
        log.info("добавлен пользователь с id {}", user.id);
        return getUserById(user.id);  //todo как это работает?
    }

    public UserDto updateUser(Long id, Map<String, String> fields) {
        UserDto user = getUserById(id);
        if (fields.containsKey("name")) {
            user.setName(fields.get("name"));
        }
        if (fields.containsKey("email")) {
            userExistsByEmail(fields.get("email"));
            user.setEmail(fields.get("email"));
        }

        userRepository.updateUser(UserMapper.fromUserDto(user));
        log.info("обновлен пользователь с id {}", id);
        return user;
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


