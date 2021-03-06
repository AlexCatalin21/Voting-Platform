package com.example.demo.votingplatform.auth.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "gender_id",referencedColumnName = "ID")
    private UserGender gender;
    @NotNull
    @Past
    private LocalDate birthdate;
}
