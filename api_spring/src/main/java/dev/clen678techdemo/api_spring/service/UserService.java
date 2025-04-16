package dev.clen678techdemo.api_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.clen678techdemo.api_spring.domain.LoginRequest;
import dev.clen678techdemo.api_spring.domain.User;
import dev.clen678techdemo.api_spring.exceptions.InvalidLoginDetailsException;
import dev.clen678techdemo.api_spring.exceptions.UserConflictException;
import dev.clen678techdemo.api_spring.exceptions.UserNotFoundException;
import dev.clen678techdemo.api_spring.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // get all users
    public List<User> getAllUsers() {
        
        return userRepository.findAll(); // returns empty list if no users found
    }

    // get user by id
    public User getUserById( ObjectId id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return user.get();
    }

    // create user
    public User createUser(User user) {
        
        // validate unique username
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UserConflictException("Username already exists");
        }

        // hash the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // set the shopping items to an empty list
        user.setShoppingItems(new ArrayList<>());

        User newUser = userRepository.save(user); // save user to database

        return newUser; // returns the saved user
    }

    // update user
    public User updateUser(ObjectId id, User user) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found"); 
        }

        User updatedUser = existingUser.get();

        // update the username
        if (!user.getUsername().equals(updatedUser.getUsername()) && user.getUsername() != null) {
            
            // validate unique username
            if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
                throw new UserConflictException("Username already exists");
            }

            updatedUser.setUsername(user.getUsername());
        }

        // update and rehash the password
        if (!passwordEncoder.matches(user.getPassword(), updatedUser.getPassword()) && user.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            updatedUser.setPassword(hashedPassword);
        }

         userRepository.save(updatedUser);

        return updatedUser; // returns the updated user
    }

    // delete user
    public void deleteUser(ObjectId id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.delete(existingUser.get());

        return;
    }
    
    // login user
    public User loginUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findUserByUsername(loginRequest.getUsername()); // need to implement this method in UserRepository (non-default)

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new InvalidLoginDetailsException("Invalid password");
        }

        return user.get();
    }
    
}
