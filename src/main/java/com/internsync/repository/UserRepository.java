package com.internsync.repository;

import com.internsync.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;   // ✅ ADD THIS

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    // 🔥 ADD THIS METHOD
    List<User> findByRole(String role);
}