package com.hackathon.mentor.repository;

import com.hackathon.mentor.models.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
}
