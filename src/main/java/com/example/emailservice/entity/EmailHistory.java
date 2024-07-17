package com.example.emailservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "email_history")
public class EmailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime history;

    @Column(nullable = true, length = 255)
    private String username;

    @Column(nullable = true, length = 255)
    private String email;

    @PrePersist
    protected void onCreate() {
        history = LocalDateTime.now();
    }
}
