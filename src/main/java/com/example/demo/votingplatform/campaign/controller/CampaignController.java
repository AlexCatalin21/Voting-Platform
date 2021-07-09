package com.example.demo.votingplatform.campaign.controller;

import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.auth.repository.UserRepository;
import com.example.demo.votingplatform.campaign.dto.CampaignAccessDto;
import com.example.demo.votingplatform.campaign.dto.CampaignDto;
import com.example.demo.votingplatform.campaign.model.Campaign;
import com.example.demo.votingplatform.campaign.repository.CampaignRepository;
import com.example.demo.votingplatform.campaign.repository.CampaignTypeRepository;
import com.example.demo.votingplatform.campaign.service.CampaignService;
import com.example.demo.votingplatform.candidates.model.Candidate;
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
    private final UserRepository userRepository;

    private User getUserById(Long Id){
        return userRepository.getOne(Id);

    }



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


    @GetMapping("get-campaigns/{user_id}")
    public List<Campaign> getUserCampaigns(@PathVariable String user_id){
        User user=getUserById(Long.valueOf(user_id));
        return campaignRepository.findAllByOwnerUser(user);
    }


    @PostMapping("/check-campaign-password")
    public ResponseEntity checkCampaignPassword(@RequestBody CampaignAccessDto campaignAccessDto){
        return campaignService.checkCampaignAccess(campaignAccessDto);
    }

    @GetMapping("get-Voters/{campaign_id}")
    public List<User> getCampaignVoters(@PathVariable String campaign_id){
        return getCampaignById(campaign_id).getVoters();
    }

    @GetMapping("/winner/{campaign_id)")
    public Candidate getWinnerCandidate(@PathVariable String campaign_id){
        return campaignService.getWinner(campaign_id);
    }

}
