package com.example.demo.votingplatform.auth.service;

import com.example.demo.votingplatform.auth.dto.LoginRequest;
import com.example.demo.votingplatform.auth.dto.RegisterRequest;
import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.auth.model.UserGender;
import com.example.demo.votingplatform.auth.repository.UserGenderRepository;
import com.example.demo.votingplatform.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGenderRepository userGenderRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserById(Long id){
        return userRepository.getOne(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    public ResponseEntity<String> register(RegisterRequest request){
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User newUser = createUserFromRequest(request);
            userRepository.flush();
            userRepository.save(newUser);

        }
        return validation;
    }

    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        UserGender userGender = userGenderRepository.getOne(Long.valueOf(request.getGenderID()));
        user.setEmail(request.getEmail());
        user.setBirthdate(request.getBirthDate());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGender(userGender);
        return user;
    }

    private ResponseEntity<String> validateRegister(RegisterRequest request) {
        User userOptional = getUserByEmail(request.getEmail());
        if (userOptional != null) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    public ResponseEntity<String> login(LoginRequest request, HttpSession session) {
        ResponseEntity<String> validation = validateLogin(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User user = getUserByEmail(request.getEmail());
            session.setAttribute("user", user);
        }
        return validation;
    }

    private ResponseEntity<String> validateLogin(LoginRequest request) {
        String errorMessage = "Invalid credentials";
        User userOptional = getUserByEmail(request.getEmail());
        if (userOptional == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), userOptional.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
