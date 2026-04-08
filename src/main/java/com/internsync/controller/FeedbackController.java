package com.internsync.controller;

import com.internsync.model.Feedback;
import com.internsync.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ── GET /api/feedbacks ──────────────────────────────────
    @GetMapping
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    // ── POST /api/feedbacks ─────────────────────────────────
    @PostMapping
    public Feedback create(@RequestBody Map<String, Object> body) {
        Feedback fb = new Feedback();
        fb.setStudentId(Long.valueOf(body.get("studentId").toString()));
        fb.setInternshipId(Long.valueOf(body.get("internshipId").toString()));
        fb.setRating(Integer.parseInt(body.get("rating").toString()));
        fb.setRemarks(body.get("remarks").toString());
        return feedbackRepository.save(fb);
    }
}
