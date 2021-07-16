package com.example.demo.votingplatform.auth.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String genderID;
    private LocalDate birthDate;
}
