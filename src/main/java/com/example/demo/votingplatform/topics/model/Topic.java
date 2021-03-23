package com.example.demo.votingplatform.topics.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "topics")
public class Topic {
    @Id
    private Long ID;
    @NotNull
    private String name;
    @NotNull
    private String description;

    private int noVotes = 0;

}
