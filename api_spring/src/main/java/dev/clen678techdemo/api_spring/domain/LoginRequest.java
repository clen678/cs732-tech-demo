package dev.clen678techdemo.api_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginRequest class representing a request to log in a user.
 * This class contains the username and password fields.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;
    
}
