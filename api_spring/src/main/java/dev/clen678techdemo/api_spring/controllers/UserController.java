package dev.clen678techdemo.api_spring.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.clen678techdemo.api_spring.domain.LoginRequest;
import dev.clen678techdemo.api_spring.domain.User;
import dev.clen678techdemo.api_spring.exceptions.InvalidLoginDetailsException;
import dev.clen678techdemo.api_spring.exceptions.UserConflictException;
import dev.clen678techdemo.api_spring.exceptions.UserNotFoundException;
import dev.clen678techdemo.api_spring.service.UserService;

/**
 * UserController class handles HTTP requests related to user operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 */
@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method is called when a GET request is made to /api/users.
     * Retrieves a list of all users from the database.
     * 
     * @return ResponseEntity containing the list of users and HTTP status code.
     *         If users are found, the list of users is returned with HTTP status OK.
     *         If no users are found, an empty list is returned with HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Method is called when a GET request is made to /api/users/{id}.
     * Retrieves a user by their ID from the database.
     * 
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the user and HTTP status code.
     *         If the user is found, the user object is returned with HTTP status OK.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
        try {
            return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method is called when a POST request is made to /api/users.
     * Creates a new user in the database.
     * 
     * @param user The user object to create.
     * @return ResponseEntity containing the created user and HTTP status code.
     *         If the user is created successfully, HTTP status CREATED is returned.
     *         If a conflict occurs (e.g., username already exists), HTTP status CONFLICT is returned.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        try {
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);

        } catch (UserConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Method is called when a PATCH request is made to /api/users/{id}.
     * Updates an existing user in the database.
     * 
     * @param id The ID of the user to update.
     * @param user The updated user object.
     * @return ResponseEntity containing the updated user and HTTP status code.
     *         If the user is found and updated successfully, HTTP status OK is returned.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User user) {
        try {
            return new ResponseEntity<User>(userService.updateUser(id, user), HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method is called when a DELETE request is made to /api/users/{id}.
     * Deletes a user by their ID from the database.
     * 
     * @param id The ID of the user to delete.
     * @return ResponseEntity with HTTP status NO CONTENT if successful,
     *         or HTTP status NOT FOUND if the user is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Method is called when a POST request is made to /api/users/login.
     * Logs in a user by validating their username and password.
     * 
     * @param loginRequest The login request containing username and password.
     * @return ResponseEntity containing the authenticated user and HTTP status code.
     *         If the login is successful, the user object is returned with HTTP status OK.
     *         If the login details are invalid, HTTP status UNAUTHORIZED is returned.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     */
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<User>(userService.loginUser(loginRequest), HttpStatus.OK);

        } catch (InvalidLoginDetailsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
