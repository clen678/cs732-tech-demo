package dev.clen678techdemo.api_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the Spring Boot application.
 * This class configures security settings for the application, including password encoding and HTTP security.
 * It uses Spring Security to manage authentication and authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * Bean for password encoding.
     * This method creates a PasswordEncoder bean using BCryptPasswordEncoder.
     * BCrypt is a strong hashing function for securely storing passwords.
     * 
     * @return PasswordEncoder bean for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder for password hashing
    }

    /**
     * Security filter chain configuration.
     * This method configures the security filter chain for the application.
     * It disables CSRF protection and allows all requests without authentication.
     * 
     * @param http HttpSecurity object for configuring security settings.
     * @return SecurityFilterChain object representing the security filter chain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not recommended for production)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests without authentication
            );
        return http.build();
    }
}
