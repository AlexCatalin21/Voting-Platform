package com.example.demo.votingplatform.auth.service;

import com.example.demo.votingplatform.auth.model.User;
import com.example.demo.votingplatform.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User getUserById(Long Id){
        return userRepository.getOne(Id);
    }
}
