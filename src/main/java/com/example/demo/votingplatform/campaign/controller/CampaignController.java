package com.example.demo.votingplatform.campaign.controller;

import com.example.demo.votingplatform.campaign.dto.CampaignAccessDto;
import com.example.demo.votingplatform.campaign.dto.CampaignDto;
import com.example.demo.votingplatform.campaign.model.Campaign;
import com.example.demo.votingplatform.campaign.repository.CampaignRepository;
import com.example.demo.votingplatform.campaign.repository.CampaignTypeRepository;
import com.example.demo.votingplatform.campaign.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/campaign")
public class CampaignController {
    private final CampaignService campaignService;
    private final CampaignTypeRepository campaignTypeRepository;
    private final CampaignRepository campaignRepository;



    @PostMapping("/add-campaign")
    public ResponseEntity<String> addCampaign(@RequestBody CampaignDto campaignDto){
        campaignService.addCampaign(campaignDto,campaignTypeRepository.getOne(campaignDto.getCampaignTypeId()));
        return new ResponseEntity<>("Candidates campaign success", HttpStatus.OK);
    }


    @GetMapping("/get-campaigns")
    public List<Campaign> getCampaign(){
        return campaignRepository.findAll();
    }


    @GetMapping("/get-campaign/{campaignId}")
    public Campaign getCampaignById(@PathVariable("campaignId") String campaignId){ return campaignRepository.getOne(Long.valueOf(campaignId));
    }


    @PostMapping("/check-campaign-password")
    public ResponseEntity checkCampaignPassword(@RequestBody CampaignAccessDto campaignAccessDto){
        return campaignService.checkCampaignAccess(campaignAccessDto);
    }

}
