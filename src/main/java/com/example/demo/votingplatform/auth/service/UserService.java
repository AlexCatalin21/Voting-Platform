package com.example.demo.votingplatform.auth.service;

import com.example.demo.votingplatform.auth.dto.LoginRequest;
import com.example.demo.votingplatform.auth.dto.RegisterRequest;
import com.example.demo.votingplatform.auth.model.AppUser;
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


    public AppUser getUserById(Long Id){
        return userRepository.getOne(Id);
    }
    public AppUser getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    public ResponseEntity<String> register(RegisterRequest request){
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            AppUser newAppUser = createUserFromRequest(request);
            userRepository.flush();
            userRepository.save(newAppUser);

        }
        return validation;
    }

    private AppUser createUserFromRequest(RegisterRequest request) {
        AppUser appUser = new AppUser();
        UserGender userGender = userGenderRepository.getOne(Long.valueOf(request.getGenderID()));
        appUser.setEmail(request.getEmail());
        appUser.setBirthdate(request.getBirthDate());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setGender(userGender);
        return appUser;
    }

    private ResponseEntity<String> validateRegister(RegisterRequest request) {
        AppUser appUserOptional = getUserByEmail(request.getEmail());
        if (appUserOptional != null) {
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
            AppUser appUser = getUserByEmail(request.getEmail());
            session.setAttribute("user", appUser);
        }
        return validation;
    }

    private ResponseEntity<String> validateLogin(LoginRequest request) {
        String errorMessage = "Invalid credentials";
        AppUser appUserOptional = getUserByEmail(request.getEmail());
        if (appUserOptional == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), appUserOptional.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
