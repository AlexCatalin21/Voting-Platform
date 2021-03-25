package com.example.demo.votingplatform.topics.service;

import com.example.demo.votingplatform.topics.dto.TopicDto;
import com.example.demo.votingplatform.topics.model.Topic;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    public Topic createTopicFromDto(Topic newTopic, TopicDto topicDto){
        newTopic.setName(topicDto.getName());
        newTopic.setDescription(topicDto.getDescription());
        newTopic.setNoVotes(0);
        return newTopic;
    }

}
