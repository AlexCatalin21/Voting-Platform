package com.example.demo.votingplatform.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email,password;
}