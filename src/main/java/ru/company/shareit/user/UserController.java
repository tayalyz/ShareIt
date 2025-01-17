package ru.company.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.company.shareit.user.dto.UserDto;
import ru.company.shareit.user.dto.UserUpdateDto;
import ru.company.shareit.user.service.UserService;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto user) {
        return userService.addUser(user);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
