package com.example.demo.votingplatform.candidates.dto;

import lombok.Data;

@Data
public class CandidateVoteDto {
    private String id, voterId, campaignId;
}
