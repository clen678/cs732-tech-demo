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

/**
 * UserService class handles business logic related to user operations.
 * It provides methods for creating, retrieving, updating, and deleting users,
 * as well as logging in users and managing their shopping items.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Retrieves a list of all users from the database.
     * 
     * @return List<User> containing all users in the database.
     *         If no users are found, an empty list is returned.
     */
    public List<User> getAllUsers() {
        
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID from the database.
     * 
     * @param id The MongoDB document ID of the user to retrieve.
     * @return User object representing the user with the specified ID.
     *         If the user is not found, a UserNotFoundException is thrown.
     * @throws UserNotFoundException if the user is not found in the database.
     */
    public User getUserById( ObjectId id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return user.get();
    }

    /**
     * Creates a new user in the database.
     * 
     * @param user The User object containing the user's details.
     * @return User object representing the newly created user.
     *         If the username already exists, a UserConflictException is thrown.
     * @throws UserConflictException if the username already exists in the database.
     */
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

    /**
     * Updates an existing user in the database.
     * 
     * @param id The MongoDB document ID of the user to update.
     * @param user The User object containing the updated user's details.
     * @return User object representing the updated user.
     *         If the user is not found, a UserNotFoundException is thrown.
     *         If the username already exists, a UserConflictException is thrown.
     * @throws UserNotFoundException if the user is not found in the database.
     * @throws UserConflictException if the username already exists in the database.
     */
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

    /**
     * Deletes a user from the database.
     * 
     * @param id The MongoDB document ID of the user to delete.
     * @throws UserNotFoundException if the user is not found in the database.
     */
    public void deleteUser(ObjectId id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.delete(existingUser.get());

        return;
    }
    
    /**
     * Logs in a user by validating their username and password.
     * 
     * @param loginRequest The LoginRequest object containing the username and password.
     * @return User object representing the authenticated user.
     *         If the username is not found, a UserNotFoundException is thrown.
     *         If the password is invalid, an InvalidLoginDetailsException is thrown.
     * @throws UserNotFoundException if the user is not found in the database.
     * @throws InvalidLoginDetailsException if the password is invalid.
     */
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
