package dev.clen678techdemo.api_spring.exceptions;

/**
 * Custom exception class for handling user not found scenarios.
 * This exception is thrown when a user is not found in the system.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor for UserNotFoundException.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
