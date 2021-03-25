package com.example.demo.votingplatform.campaign.service;

import com.example.demo.votingplatform.auth.service.UserService;
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
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CandidateRepository candidateRepository;
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final CampaignTypeRepository campaignTypeRepository;
    private final CandidateService candidateService;
    private final TopicService topicService;


    public CampaignType getTypeById(Long Id){
        return campaignTypeRepository.getOne(Id);
    }


    public void addCandidatesCampaign(CampaignDto campaignDto){
        Campaign campaign = new Campaign();
        campaign.setName(campaignDto.getName());
        campaign.setDescription(campaignDto.getDescription());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setExpireDate(campaignDto.getExpireDate());
        campaign.setOwnerUser(userService.getUserById(campaignDto.getOwnerUserId()));
        campaign.setType(getTypeById(campaignDto.getCampaignTypeId()));

        campaignDto.getCandidateDtoList().forEach( candidateDto -> {
            Candidate candidate = candidateService.createCandidateFromDto(new Candidate(), candidateDto);
            candidateRepository.save(candidate);
            campaign.addCandidate(candidate);
        });

        Campaign savedCampaign= campaignRepository.save(campaign);
        campaignRepository.flush();

        campaignRepository.save(savedCampaign);

    }


    public void addTopicCampaign(CampaignDto campaignDto){
        Campaign campaign = new Campaign();
        campaign.setName(campaignDto.getName());
        campaign.setDescription(campaignDto.getDescription());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setExpireDate(campaignDto.getExpireDate());
        campaign.setOwnerUser(userService.getUserById(campaignDto.getOwnerUserId()));
        campaign.setType(getTypeById(campaignDto.getCampaignTypeId()));

        campaignDto.getTopicDtoList().forEach( topicDto -> {
            Topic topic = topicService.createTopicFromDto(new Topic(), topicDto);
            topicRepository.save(topic);
            campaign.addTopic(topic);
        });

        Campaign savedCampaign= campaignRepository.save(campaign);
        campaignRepository.flush();

        campaignRepository.save(savedCampaign);
    }
}
