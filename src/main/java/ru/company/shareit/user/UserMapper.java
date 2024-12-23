package ru.company.shareit.user;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.id,
                user.name,
                user.email
        );
    }

    public static User fromUserDto(UserDto userDto) {
        return new User(
                userDto.id,
                userDto.name,
                userDto.email
        );
    }
}
