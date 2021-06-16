package com.example.demo.votingplatform.candidates.controller;


import com.example.demo.votingplatform.auth.model.AppUser;
import com.example.demo.votingplatform.auth.repository.UserRepository;
import com.example.demo.votingplatform.campaign.model.Campaign;
import com.example.demo.votingplatform.campaign.repository.CampaignRepository;
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
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;


    @PutMapping("/vote")
    public ResponseEntity<String> voteCandidate(@RequestBody CandidateVoteDto candidateVoteDto){
        System.out.println(candidateVoteDto);
        Campaign campaign = campaignRepository.getOne(Long.valueOf(candidateVoteDto.getCampaignId()));
        for(AppUser appUser : campaign.getVoters()){
            if(appUser.equals(userRepository.getOne(Long.valueOf(candidateVoteDto.getVoterId())))){
                System.out.println("This appUser already voted!");
                return new ResponseEntity<>("You already voted!" ,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        campaign.addVoter(userRepository.getOne(Long.valueOf(candidateVoteDto.getVoterId())));
        campaignRepository.save(campaign);
        Candidate candidate = candidateRepository.getOne(Long.valueOf(candidateVoteDto.getId()));
        candidate.setNoVotes(candidate.getNoVotes() + 1);
        candidateRepository.save(candidate);


        return new ResponseEntity<>("Vote success", HttpStatus.OK);
    }
}
