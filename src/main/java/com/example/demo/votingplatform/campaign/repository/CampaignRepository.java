package com.example.demo.votingplatform.campaign.repository;

import com.example.demo.votingplatform.campaign.model.Campaign;
import com.example.demo.votingplatform.campaign.model.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {

    @Transactional
    public List<Campaign> findAll();
}
