package com.example.demo.votingplatform.auth.repository;

import com.example.demo.votingplatform.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser getByEmail(String email);
}
