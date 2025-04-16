package dev.clen678techdemo.api_spring.exceptions;

/**
 * Custom exception class for handling conflicts in shopping items.
 * This exception is thrown when there is a conflict with the shopping item, such as a duplicate name.
 */
public class ShoppingItemConflictException extends RuntimeException {

    /**
     * Constructor for ShoppingItemConflictException.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public ShoppingItemConflictException(String message) {
        super(message);
    }
}
