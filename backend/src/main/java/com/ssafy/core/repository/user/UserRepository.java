package com.ssafy.core.repository.user;

import com.ssafy.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}