package com.example.demo.votingplatform.campaign.model;


import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.candidates.model.Candidate;
import com.example.demo.votingplatform.topics.model.Topic;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name="campaigns")
public class Campaign {
    @Id
    private Long ID;
    @NotNull
    private String name;
    @NotNull
    private Date startDate;
    @NotNull
    private Date expireDate;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "campaign_type_id", referencedColumnName = "ID")
    private CampaignType type;
    @NotNull
    private String description;
    @ManyToMany
    @JoinColumn(name = "candidate_id", referencedColumnName = "ID")
    private List<Candidate> candidates = new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "topic_id",  referencedColumnName = "ID")
    private List<Topic> topics = new ArrayList<>();
    @NotNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User ownerUser;
    @ManyToMany
    @JoinColumn(name = "voter_id", referencedColumnName = "ID")
    private List<User> voters = new ArrayList<>();


}
