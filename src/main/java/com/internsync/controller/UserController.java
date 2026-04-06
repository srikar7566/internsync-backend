package com.internsync.controller;

import com.internsync.model.User;
import com.internsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET /api/users/students  — returns all users with role = student (no passwords)
    @GetMapping("/students")
    public List<Map<String, Object>> getStudents() {
        return userRepository.findAll().stream()
                .filter(u -> "student".equals(u.getRole()))
                .map(u -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id",    u.getId());
                    map.put("name",  u.getName());
                    map.put("email", u.getEmail());
                    map.put("role",  u.getRole());
                    return map;
                })
                .collect(Collectors.toList());
    }

    // GET /api/users  — returns all users (no passwords)
    @GetMapping
    public List<Map<String, Object>> getAll() {
        return userRepository.findAll().stream()
                .map(u -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id",    u.getId());
                    map.put("name",  u.getName());
                    map.put("email", u.getEmail());
                    map.put("role",  u.getRole());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
