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
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // ── GET /api/applications ───────────────────────────────
    @GetMapping
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    // ── POST /api/applications ──────────────────────────────
    @PostMapping
    public Application apply(@RequestBody Map<String, Object> body) {
        Application app = new Application();
        app.setStudentId(Long.valueOf(body.get("studentId").toString()));
        app.setInternshipId(Long.valueOf(body.get("internshipId").toString()));
        app.setStatus("pending");
        return applicationRepository.save(app);
    }

    // ── PATCH /api/applications/{id}/status ─────────────────
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
