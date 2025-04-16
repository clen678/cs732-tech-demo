package dev.clen678techdemo.api_spring.exceptions;

public class ShoppingItemNotFoundException extends RuntimeException {
    public ShoppingItemNotFoundException(String message) {
        super(message);
    }
}
