package com.example.demo.votingplatform.campaign.controller;

import com.example.demo.votingplatform.campaign.dto.CampaignDto;
import com.example.demo.votingplatform.campaign.service.CampaignService;
import com.example.demo.votingplatform.candidates.service.CandidateService;
import com.example.demo.votingplatform.topics.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/campaign")
public class CampaignController {
    private final CampaignService campaignService;
    private final CandidateService candidateService;
    private final TopicService topicService;

    @PostMapping("/add-candidates-camp")
    public ResponseEntity<String> addCabdidatesCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.addCandidatesCampaign(campaignDto);
        return new ResponseEntity<>("", HttpStatus.OK);


    }


}
