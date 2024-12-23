package ru.company.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.company.shareit.util.IdGenerator;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> addUser(User user) {
        user.setId(IdGenerator.INSTANCE.generate(User.class));
        return Optional.ofNullable(users.put(user.id, user));
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.ofNullable(users.put(user.id, user));
    }

    @Override
    public void deleteUserById(Long id) {
        users.remove(id);
    }

    @Override
    public Map<Long, User> getAllUsers() {
        return users;
    }
}
