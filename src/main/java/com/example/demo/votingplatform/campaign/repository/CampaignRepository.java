package com.example.demo.votingplatform.campaign.repository;

import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {

    @Transactional
    public List<Campaign> findAll();

    public List<Campaign> findAllByOwnerUser(User user);
}
