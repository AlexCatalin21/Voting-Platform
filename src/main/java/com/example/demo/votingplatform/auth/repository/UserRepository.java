package com.example.demo.votingplatform.auth.repository;

import com.example.demo.votingplatform.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByEmail(String email);
}
