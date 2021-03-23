package com.example.demo.votingplatform.candidates.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "candidates")
public class Candidate {
    @Id
    private Long ID;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private Date birth_date;
    private String description;
    private String electoral_speech;
    private Long no_votes;

}
