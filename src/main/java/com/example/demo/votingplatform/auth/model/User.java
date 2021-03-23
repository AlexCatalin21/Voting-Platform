package com.example.demo.votingplatform.auth.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "users")
public class User {
    @Id
    private Long ID;
    @NotNull
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
    private Date birthdate;
}
