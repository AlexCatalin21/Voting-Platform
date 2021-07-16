package com.example.demo.votingplatform.candidates.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @PastOrPresent
    private Date birthDate;
    private String description;
    private String electoralSpeech;
    private int noVotes;

}
