package dev.clen678techdemo.api_spring.exceptions;

/**
 * Custom exception class for handling invalid login details.
 * This exception is thrown when the user provides invalid login credentials.
 */
public class InvalidLoginDetailsException extends RuntimeException {

    /**
     * Constructor for InvalidLoginDetailsException.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidLoginDetailsException(String message) {
        super(message);
    }
}
