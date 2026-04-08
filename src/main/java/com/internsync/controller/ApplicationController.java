package com.internsync.controller;

import com.internsync.model.Application;
import com.internsync.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> apply(@RequestBody Map<String, Object> body) {
        try {
            Application app = new Application();
            app.setStudentId(Long.valueOf(body.get("studentId").toString()));
            app.setInternshipId(Long.valueOf(body.get("internshipId").toString()));
            app.setStatus("pending");
            Application saved = applicationRepository.save(app);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Optional<Application> optional = applicationRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Application app = optional.get();
        app.setStatus(body.get("status"));
        return ResponseEntity.ok(applicationRepository.save(app));
    }
}