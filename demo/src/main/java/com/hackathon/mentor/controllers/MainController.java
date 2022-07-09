package com.hackathon.mentor.controllers;

import com.hackathon.mentor.models.Mentor;
import com.hackathon.mentor.models.User;
import com.hackathon.mentor.repository.MentorRepository;
import com.hackathon.mentor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/mentors")
    public ResponseEntity<?> getMentors() {
        List<Mentor> mentors = mentorRepository.getAll();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }


    @GetMapping("/mentors/{id}")
    public ResponseEntity<?> getMentorById(@PathVariable("id") Long id) {
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        if (mentor == null) {
            return new ResponseEntity<>("Not Found!!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }
}
