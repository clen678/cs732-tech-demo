package dev.clen678techdemo.api_spring.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.clen678techdemo.api_spring.domain.User;

/**
 * UserRepository interface for performing CRUD operations on User documents in MongoDB.
 * It extends the MongoRepository interface provided by Spring Data MongoDB.
 * The ObjectId type is used as the ID type for the User documents.
 */
@Repository // this annotation indicates that this class will be used to interact with the database
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByUsername(String username); // spring automatically implements this method based on the name of the method 
}
