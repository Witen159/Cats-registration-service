package ru.itmo.kotiki.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
