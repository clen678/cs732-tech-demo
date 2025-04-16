package dev.clen678techdemo.api_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Spring Boot application.
 * This class contains the main method which is the entry point of the application.
 * It uses Spring Boot's SpringApplication.run() method to launch the application.
 * In VSCode or IntelliJ, you can run this class to start the application.
 */
@SpringBootApplication
public class ApiSpringApplication {

	/**
	 * Main method to run the Spring Boot application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiSpringApplication.class, args);
	}

}
