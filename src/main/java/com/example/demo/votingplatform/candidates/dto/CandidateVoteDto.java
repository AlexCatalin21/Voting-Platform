package com.example.demo.votingplatform.candidates.dto;

import lombok.Data;

@Data
public class CandidateVoteDto {
    private String id;
    private String voterId;
    private String campaignId;
}
