package dev.clen678techdemo.api_spring.exceptions;

/**
 * Custom exception class for handling cases where a shopping item is not found.
 * This exception is thrown when a requested shopping item does not exist in the database.
 */
public class ShoppingItemNotFoundException extends RuntimeException {

    /**
     * Constructor for ShoppingItemNotFoundException.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public ShoppingItemNotFoundException(String message) {
        super(message);
    }
}
