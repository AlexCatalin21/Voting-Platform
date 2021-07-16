package com.example.demo.votingplatform.candidates.service;

import com.example.demo.votingplatform.candidates.dto.CandidateDto;
import com.example.demo.votingplatform.candidates.model.Candidate;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    public Candidate createCandidateFromDto(Candidate newCandidate,CandidateDto candidateDto){
        newCandidate.setFirstName(candidateDto.getFirstName());
        newCandidate.setLastName(candidateDto.getLastName());
        newCandidate.setBirthDate(candidateDto.getBirthdate());
        newCandidate.setDescription(candidateDto.getCandidateDescription());
        newCandidate.setElectoralSpeech(candidateDto.getElectoralSpeech());
        newCandidate.setNoVotes(0);
        return newCandidate;
    }
}
