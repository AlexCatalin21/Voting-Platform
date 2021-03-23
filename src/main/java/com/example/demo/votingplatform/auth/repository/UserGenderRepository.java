package com.example.demo.votingplatform.auth.repository;

import com.example.demo.votingplatform.auth.model.UserGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenderRepository extends JpaRepository<UserGender,Long> {
}
