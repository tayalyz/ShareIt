package ru.company.shareit.user;

import java.util.*;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(Long id, Map<String, String> fields);

    void deleteUserById(Long id);
}


