package ru.company.shareit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.shareit.user.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
