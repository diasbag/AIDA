package com.hackathon.mentor.controllers;

import com.hackathon.mentor.models.Mentor;
import com.hackathon.mentor.models.Post;
import com.hackathon.mentor.models.User;
import com.hackathon.mentor.payload.request.PostRequest;
import com.hackathon.mentor.repository.MentorRepository;
import com.hackathon.mentor.repository.PostRepository;
import com.hackathon.mentor.repository.UserRepository;
import com.hackathon.mentor.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postRepository.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getEmail();
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setDate(Date.from(Instant.now()));
        post.setArticle(postRequest.getArticle());
        User user = userRepository.getByEmail(email);
        Mentor mentor = mentorRepository.findByUser(user.getId());
        post.setMentor(mentor);
        postRepository.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
