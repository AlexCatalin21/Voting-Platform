package com.example.demo.votingplatform.candidates.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CandidateDto {
    private String firstName, lastName, description, electoralSpeech;
    private Date birthdate;
}
