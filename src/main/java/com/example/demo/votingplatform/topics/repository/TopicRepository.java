package com.example.demo.votingplatform.topics.repository;

import com.example.demo.votingplatform.topics.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
