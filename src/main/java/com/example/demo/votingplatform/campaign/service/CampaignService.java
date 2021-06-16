package com.example.demo.votingplatform.campaign.service;

import com.example.demo.votingplatform.auth.service.UserService;
import com.example.demo.votingplatform.campaign.dto.CampaignAccessDto;
import com.example.demo.votingplatform.campaign.dto.CampaignDto;
import com.example.demo.votingplatform.campaign.model.Campaign;
import com.example.demo.votingplatform.campaign.model.CampaignType;
import com.example.demo.votingplatform.campaign.repository.CampaignRepository;
import com.example.demo.votingplatform.campaign.repository.CampaignTypeRepository;
import com.example.demo.votingplatform.candidates.model.Candidate;
import com.example.demo.votingplatform.candidates.repository.CandidateRepository;
import com.example.demo.votingplatform.candidates.service.CandidateService;
import com.example.demo.votingplatform.topics.model.Topic;
import com.example.demo.votingplatform.topics.repository.TopicRepository;
import com.example.demo.votingplatform.topics.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CandidateRepository candidateRepository;
    private final TopicRepository topicRepository;
    private final PasswordEncoder passwordEncoder;
    private final CampaignTypeRepository campaignTypeRepository;
    private final CandidateService candidateService;
    private final TopicService topicService;
    private final UserService userService;

    public CampaignType getTypeById(String Id) {
        return campaignTypeRepository.getOne(Long.valueOf(Id));
    }


    public Campaign addCampaign(CampaignDto campaignDto, CampaignType campaignType) {
        if(validateCampaignPassword(campaignDto).getStatusCode().equals(HttpStatus.OK)) {
            Campaign campaign = new Campaign();
            campaign.setName(campaignDto.getName());
            campaign.setDescription(campaignDto.getDescription());
            campaign.setStartDate(campaignDto.getStartDate());
            campaign.setPassword(passwordEncoder.encode(campaignDto.getPassword()));
            campaign.setExpireDate(campaignDto.getExpireDate());
            campaign.setOwnerAppUser(userService.getUserById(campaignDto.getOwnerUserId()));
            campaign.setType(getTypeById(String.valueOf(campaignDto.getCampaignTypeId())));
            if (campaignType.equals(getTypeById("1"))) {
                campaignDto.getCandidateDtoList().forEach(candidateDto -> {
                    Candidate candidate = candidateService.createCandidateFromDto(new Candidate(), candidateDto);
                    candidateRepository.save(candidate);
                    campaign.addCandidate(candidate);
                });
            } else if (campaignType.equals(getTypeById("2"))) {
                campaignDto.getTopicDtoList().forEach(topicDto -> {
                    Topic topic = topicService.createTopicFromDto(new Topic(), topicDto);
                    topicRepository.save(topic);
                    campaign.addTopic(topic);
                });

            }

            Campaign savedCampaign = campaignRepository.save(campaign);
            campaignRepository.flush();

            campaignRepository.save(savedCampaign);
            return campaign;
        }
        return null;
    }


    private ResponseEntity<String> validateCampaignPassword(CampaignDto campaignDto) {
        if (!campaignDto.getPassword().equals(campaignDto.getConfirmedPassword())) {
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Campaign successful", HttpStatus.OK);
    }

    public ResponseEntity<String> checkCampaignAccess(CampaignAccessDto campaignAccessDto) {
        String errorMessage = "Invalid password";
        Campaign campaign = campaignRepository.getOne(campaignAccessDto.getId());
        if (!passwordEncoder.matches(campaignAccessDto.getPassword(), campaign.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("You have access", HttpStatus.OK);
    }

}
