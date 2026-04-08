package com.internsync.controller;

import com.internsync.model.Internship;
import com.internsync.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internships")
@CrossOrigin(origins = "*")
public class InternshipController {

    @Autowired
    private InternshipRepository internshipRepository;

    // ── GET /api/internships ────────────────────────────────
    @GetMapping
    public List<Internship> getAll() {
        return internshipRepository.findAll();
    }

    // ── POST /api/internships ───────────────────────────────
    @PostMapping
    public Internship create(@RequestBody Map<String, String> body) {
        Internship internship = new Internship();
        internship.setTitle(body.get("title"));
        internship.setCompany(body.get("company"));
        internship.setDescription(body.get("description"));
        internship.setStatus("open");
        return internshipRepository.save(internship);
    }

    // ── DELETE /api/internships/{id} ────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!internshipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        internshipRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
