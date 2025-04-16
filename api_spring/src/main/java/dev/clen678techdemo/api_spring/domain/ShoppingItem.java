package dev.clen678techdemo.api_spring.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "shopping_items")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class ShoppingItem {
    
    // we dont need @Id annotation because of @DocumentReference in User.java??
    private ObjectId id; // mongoDB creates a unique identifier for each document of type ObjectId

    private String name;

    private Integer quantity;

    private String unit; 
}
