package com.example.demo.votingplatform.campaign.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "campaign_type")
public class CampaignType {

    public enum Type{
        Candidate,Topic
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull @Enumerated(EnumType.STRING)
    private Type type;
}
