package ru.company.shareit.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {

    Long id;

    String name;

    String email;
}
