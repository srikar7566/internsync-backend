package com.internsync.controller;

import com.internsync.model.Task;
import com.internsync.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // ── GET /api/tasks ──────────────────────────────────────
    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    // ── POST /api/tasks ─────────────────────────────────────
    @PostMapping
    public Task create(@RequestBody Map<String, Object> body) {
        Task task = new Task();
        task.setTitle(body.get("title").toString());
        task.setDescription(body.get("description").toString());
        task.setStudentId(Long.valueOf(body.get("studentId").toString()));
        task.setInternshipId(Long.valueOf(body.get("internshipId").toString()));
        task.setStatus("todo");
        return taskRepository.save(task);
    }

    // ── PATCH /api/tasks/{id}/status ────────────────────────
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Task task = optional.get();
        task.setStatus(body.get("status"));
        return ResponseEntity.ok(taskRepository.save(task));
    }

    // ── PATCH /api/tasks/{id}/report ────────────────────────
    @PatchMapping("/{id}/report")
    public ResponseEntity<?> submitReport(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Task task = optional.get();
        task.setReport(body.get("report"));
        task.setStatus("done");
        return ResponseEntity.ok(taskRepository.save(task));
    }
}
