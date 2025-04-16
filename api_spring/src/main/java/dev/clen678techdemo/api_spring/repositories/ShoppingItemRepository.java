package dev.clen678techdemo.api_spring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.clen678techdemo.api_spring.domain.ShoppingItem;

@Repository
public interface ShoppingItemRepository extends MongoRepository<ShoppingItem, ObjectId> {
    
}
