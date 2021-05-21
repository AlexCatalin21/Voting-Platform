package com.example.demo.votingplatform.candidates.controller;


import com.example.demo.votingplatform.candidates.dto.CandidateVoteDto;
import com.example.demo.votingplatform.candidates.model.Candidate;
import com.example.demo.votingplatform.candidates.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidatesController {

    private final CandidateRepository candidateRepository;


    @PutMapping("/vote")
    public ResponseEntity<String> voteCandidate(@RequestBody CandidateVoteDto candidateVoteDto){
        System.out.println(candidateVoteDto);
        Candidate candidate = candidateRepository.getOne(Long.valueOf(candidateVoteDto.getId()));
        System.out.println(candidate.getFirst_name() + candidate.getLast_name());
        candidate.setNoVotes(candidate.getNoVotes() + 1);
        candidateRepository.save(candidate);
        return new ResponseEntity<>("Vote success", HttpStatus.OK);
    }
}
