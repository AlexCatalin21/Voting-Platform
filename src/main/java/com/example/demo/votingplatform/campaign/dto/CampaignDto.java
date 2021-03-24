package com.example.demo.votingplatform.campaign.dto;

import com.example.demo.votingplatform.candidates.dto.CandidateDto;
import com.example.demo.votingplatform.topics.dto.TopicDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampaignDto {
    private String name,description, campaignTypeId,password, confirmedPassword;
    private Date startDate, expireDate;
    private Long ownerUserId;
    private List<CandidateDto> candidateDtoList;
    private List<TopicDto> topicDtoList;
}
