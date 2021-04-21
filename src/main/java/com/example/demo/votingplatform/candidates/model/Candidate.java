package com.example.demo.votingplatform.candidates.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    @PastOrPresent
    private Date birth_date;
    private String description;
    private String electoralSpeech;
    private int noVotes=0;

}
