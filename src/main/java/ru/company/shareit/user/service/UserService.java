package ru.company.shareit.user.service;

import ru.company.shareit.user.dto.UserDto;
import ru.company.shareit.user.dto.UserUpdateDto;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    void deleteUserById(Long id);
}


