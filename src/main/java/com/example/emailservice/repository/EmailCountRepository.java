package com.example.emailservice.repository;

import com.example.emailservice.entity.EmailCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCountRepository extends JpaRepository<EmailCount, Long>  {
}
