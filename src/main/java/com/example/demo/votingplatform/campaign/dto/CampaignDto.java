package com.example.demo.votingplatform.campaign.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CampaignDto {
    private String name,description, campaignTypeId;
    private Date startDate, expireDate;
}
