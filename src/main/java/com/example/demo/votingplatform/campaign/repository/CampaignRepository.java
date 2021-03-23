package com.example.demo.votingplatform.campaign.repository;

import com.example.demo.votingplatform.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
