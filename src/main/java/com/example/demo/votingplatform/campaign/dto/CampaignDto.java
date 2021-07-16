package com.example.demo.votingplatform.campaign.dto;

import com.example.demo.votingplatform.candidates.dto.CandidateDto;
import com.example.demo.votingplatform.topics.dto.TopicDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampaignDto {
    private String name;
    private String description;
    private String password;
    private String confirmedPassword;
    private Date startDate;
    private Date expireDate;
    private Long ownerUserId;
    private Long campaignTypeId;
    private List<CandidateDto> candidateDtoList;
    private List<TopicDto> topicDtoList;
}
