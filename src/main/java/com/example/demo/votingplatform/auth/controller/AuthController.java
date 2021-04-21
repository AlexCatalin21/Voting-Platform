package com.example.demo.votingplatform.auth.controller;

import com.example.demo.votingplatform.auth.dto.LoginRequest;
import com.example.demo.votingplatform.auth.dto.RegisterRequest;
import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request, HttpSession session) {
        return userService.login(request, session);
    }

    @GetMapping("/is-logged-in")
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/");
    }

    @GetMapping("/get-user/{email}")
    public User returnUser(@PathVariable String email ) {
        return userService.getUserByEmail(email);
    }
}
