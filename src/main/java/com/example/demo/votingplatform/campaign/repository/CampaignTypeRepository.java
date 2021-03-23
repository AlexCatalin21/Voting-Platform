package com.example.demo.votingplatform.campaign.repository;

import com.example.demo.votingplatform.campaign.model.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignTypeRepository extends JpaRepository<CampaignType, Long> {
}
