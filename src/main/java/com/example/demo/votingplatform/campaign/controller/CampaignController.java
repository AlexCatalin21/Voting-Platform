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

    private User getUserById(Long id){
        return userRepository.getOne(id);

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


    @GetMapping("get-campaigns/{userId}")
    public List<Campaign> getUserCampaigns(@PathVariable String userId){
        User user=getUserById(Long.valueOf(userId));
        return campaignRepository.findAllByOwnerUser(user);
    }


    @PostMapping("/check-campaign-password")
    public ResponseEntity<String> checkCampaignPassword(@RequestBody CampaignAccessDto campaignAccessDto){
        return campaignService.checkCampaignAccess(campaignAccessDto);
    }

    @GetMapping("get-Voters/{campaignId}")
    public List<User> getCampaignVoters(@PathVariable String campaignId){
        return getCampaignById(campaignId).getVoters();
    }

    @GetMapping("/winner/{campaignId)")
    public Candidate getWinnerCandidate(@PathVariable String campaignId){
        return campaignService.getWinner(campaignId);
    }

}
