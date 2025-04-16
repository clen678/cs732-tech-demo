package dev.clen678techdemo.api_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.clen678techdemo.api_spring.service.UserService;

// @CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
}
