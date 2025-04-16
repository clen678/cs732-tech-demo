package dev.clen678techdemo.api_spring.exceptions;

/**
 * Custom exception class for handling user conflicts.
 * This exception is thrown when there is a conflict with the user, such as a duplicate username or email.
 */
public class UserConflictException extends RuntimeException {

    /**
     * Constructor for UserConflictException.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public UserConflictException(String message) {
        super(message);
    }
}
