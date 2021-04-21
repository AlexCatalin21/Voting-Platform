package com.example.demo.votingplatform.auth.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String email,firstName,lastName,password,confirmPassword,genderID;
    private LocalDate birthDate;
}
