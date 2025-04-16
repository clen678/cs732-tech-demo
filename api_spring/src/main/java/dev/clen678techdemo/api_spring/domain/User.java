package dev.clen678techdemo.api_spring.domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users") // should match the mongoDB collection name
@Data // lombok annotation to generate getters and setters
@AllArgsConstructor // lombok annotation to generate constructors
@NoArgsConstructor // lombok annotation to generate no-args constructor
public class User {
    
    @Id // mongoDB will use this field as the primary key for the document
    private ObjectId id;

    @Indexed(unique = true)
    private String username;

    private String password;
    
    private List<ShoppingItem> shoppingItems; // list of shopping items associated with the user
}
