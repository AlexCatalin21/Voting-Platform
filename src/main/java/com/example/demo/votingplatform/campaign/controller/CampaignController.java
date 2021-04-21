package com.example.demo.votingplatform.campaign.controller;

import com.example.demo.votingplatform.campaign.dto.CampaignDto;
import com.example.demo.votingplatform.campaign.repository.CampaignTypeRepository;
import com.example.demo.votingplatform.campaign.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/campaign")
public class CampaignController {
    private final CampaignService campaignService;
    private final CampaignTypeRepository campaignTypeRepository;


    @PostMapping("/add-campaign")
    public ResponseEntity<String> addCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.addCampaign(campaignDto,campaignTypeRepository.getOne(campaignDto.getCampaignTypeId()));
        return new ResponseEntity<>("Candidates campaign success", HttpStatus.OK);
    }


}
