package com.example.demo.votingplatform.candidates.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CandidateDto {
    private String firstName;
    private String lastName;
    private String candidateDescription;
    private String electoralSpeech;
    private Date birthdate;
}
