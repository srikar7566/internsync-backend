package com.internsync.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "internship_id", nullable = false)
    private Long internshipId;

    @Column(nullable = false)
    private String status = "todo";

    @Column(columnDefinition = "TEXT")
    private String report;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReport() { return report; }
    public void setReport(String report) { this.report = report; }
}