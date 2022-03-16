package com.polling.core.repository.user;

import com.polling.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByName(String name);
}