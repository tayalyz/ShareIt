package ru.company.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.company.shareit.user.User;

import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> getUserById(Long id);

    Optional<User> addUser(User user);

    Optional<User> updateUser(User user);

    Map<Long, User> getAllUsers();

    void deleteUserById(Long id);
}
