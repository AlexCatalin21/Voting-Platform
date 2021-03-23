package com.example.demo.votingplatform.auth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String email,firstName,lastName,password,confirmPassword,genderID;
    private Date birthDate;
}
