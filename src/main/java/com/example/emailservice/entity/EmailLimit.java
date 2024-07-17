package com.example.emailservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "email_limit")
public class EmailLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 255)
    private String username;

    @Column(nullable = false)
    private int dailyLimit;

    @Column(nullable = false)
    private boolean isActive;
}
