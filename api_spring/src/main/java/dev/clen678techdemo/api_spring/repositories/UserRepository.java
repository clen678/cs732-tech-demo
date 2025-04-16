package dev.clen678techdemo.api_spring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.clen678techdemo.api_spring.domain.User;

@Repository // this annotation indicates that this class will be used to interact with the database
public interface UserRepository extends MongoRepository<User, ObjectId> {

}
