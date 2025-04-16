package dev.clen678techdemo.api_spring.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ShoppingItem class representing an item in the shopping list.
 */
@Document(collection = "shopping_items")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class ShoppingItem {
    
    @Indexed(unique = true)
    private String name;

    private Integer quantity;

    private String unit; 
}
