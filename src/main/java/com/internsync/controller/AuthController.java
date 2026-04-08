package com.internsync.controller;

import com.internsync.model.User;
import com.internsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ── POST /api/auth/register ─────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String name     = body.get("name");
        String email    = body.get("email");
        String password = body.get("password");
        String role     = body.get("role");

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);   // plain text – hash with BCrypt if you add Spring Security later
        user.setRole(role != null ? role : "student");

        User saved = userRepository.save(user);
        return ResponseEntity.ok(safeUser(saved));
    }

    // ── POST /api/auth/login ────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email    = body.get("email");
        String password = body.get("password");

        Optional<User> found = userRepository.findByEmail(email);
        if (found.isEmpty() || !found.get().getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        return ResponseEntity.ok(safeUser(found.get()));
    }

    // Return user object without the password field
    private Map<String, Object> safeUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",    user.getId());
        map.put("name",  user.getName());
        map.put("email", user.getEmail());
        map.put("role",  user.getRole());
        return map;
    }
}
