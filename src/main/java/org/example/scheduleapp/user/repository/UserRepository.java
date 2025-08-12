package org.example.scheduleapp.user.repository;

import org.example.scheduleapp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
