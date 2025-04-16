package dev.clen678techdemo.api_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import dev.clen678techdemo.api_spring.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
}
