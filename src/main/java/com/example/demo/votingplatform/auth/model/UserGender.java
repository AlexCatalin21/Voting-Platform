package com.example.demo.votingplatform.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "user_gender")
public class UserGender {

    public enum Gender{
        Male,Female
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
