package dev.clen678techdemo.api_spring.exceptions;

public class InvalidLoginDetailsException extends RuntimeException {
    public InvalidLoginDetailsException(String message) {
        super(message);
    }
}
