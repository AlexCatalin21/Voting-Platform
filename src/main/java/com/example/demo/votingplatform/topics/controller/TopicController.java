package com.example.demo.votingplatform.topics.controller;


import com.example.demo.votingplatform.topics.dto.TopicVoteDto;
import com.example.demo.votingplatform.topics.model.Topic;
import com.example.demo.votingplatform.topics.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/topics")
public class TopicController {
    private final TopicRepository topicRepository;

    @PutMapping("/vote")
    public ResponseEntity<String> voteTopic(@RequestBody TopicVoteDto topicVoteDto){
        System.out.println(topicVoteDto);
        Topic topic = topicRepository.getOne(Long.valueOf(topicVoteDto.getId()));

        topic.setNoVotes(topic.getNoVotes() + 1);
        topicRepository.save(topic);
        return new ResponseEntity<>("Vote success", HttpStatus.OK);
    }
}
