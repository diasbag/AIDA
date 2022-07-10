package com.hackathon.mentor.security.services;

import com.hackathon.mentor.models.Mentor;
import com.hackathon.mentor.models.User;
import com.hackathon.mentor.payload.request.UpdateMentorRequest;
import com.hackathon.mentor.repository.MentorRepository;
import com.hackathon.mentor.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public class MentorService {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;

}
