package com.example.demo.votingplatform.candidates.repository;

import com.example.demo.votingplatform.candidates.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
