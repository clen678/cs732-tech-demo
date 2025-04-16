package dev.clen678techdemo.api_spring.exceptions;

public class ShoppingItemConflictException extends RuntimeException {
    public ShoppingItemConflictException(String message) {
        super(message);
    }
}
