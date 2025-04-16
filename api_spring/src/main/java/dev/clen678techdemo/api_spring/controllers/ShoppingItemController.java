package dev.clen678techdemo.api_spring.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.clen678techdemo.api_spring.domain.ShoppingItem;
import dev.clen678techdemo.api_spring.exceptions.ShoppingItemConflictException;
import dev.clen678techdemo.api_spring.exceptions.ShoppingItemNotFoundException;
import dev.clen678techdemo.api_spring.exceptions.UserNotFoundException;
import dev.clen678techdemo.api_spring.service.ShoppingItemService;

/**
 * ShoppingItemController class handles HTTP requests related to shopping item operations.
 * It provides endpoints for retrieving, adding, and deleting shopping items for a specific user.
 */
@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/users/{userId}/items")
public class ShoppingItemController {
    
    @Autowired
    private ShoppingItemService shoppingItemService;

    /**
     * Method is called when a GET request is made to /api/users/{userId}/items.
     * Retrieves a list of all shopping items for a specific user.
     * 
     * @param userId The ID of the user whose shopping items are to be retrieved.
     * @return ResponseEntity containing the list of shopping items and HTTP status code.
     *         If the user is found, the list of shopping items is returned with HTTP status OK.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     */
    @GetMapping
    public ResponseEntity<List<ShoppingItem>> getAllShoppingItemsForUser(@PathVariable ObjectId userId) {
        try {
            return new ResponseEntity<List<ShoppingItem>>(shoppingItemService.getAllShoppingItemsForUser(userId), HttpStatus.OK);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method is called when a POST request is made to /api/users/{userId}/items.
     * Adds a new shopping item to the user's shopping list.
     * 
     * @param userId The ID of the user to whom the shopping item is to be added.
     * @param newItem The new shopping item to be added.
     * @return ResponseEntity containing the added shopping item and HTTP status code.
     *         If the user is found and the item is added successfully, HTTP status CREATED is returned.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     *         If there is a conflict with the shopping item, HTTP status CONFLICT is returned.
     */
    @PostMapping
    public ResponseEntity<ShoppingItem> addShoppingItemToUser(@PathVariable ObjectId userId, @RequestBody ShoppingItem newItem) {
        try {
            return new ResponseEntity<ShoppingItem>(shoppingItemService.addShoppingItemToUser(userId, newItem), HttpStatus.CREATED);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ShoppingItemConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Method is called when a DELETE request is made to /api/users/{userId}/items/{itemName}.
     * Deletes a shopping item from the user's shopping list.
     * 
     * @param userId The ID of the user from whom the shopping item is to be deleted.
     * @param itemName The name of the shopping item to be deleted.
     * @return ResponseEntity with HTTP status NO CONTENT if the item is deleted successfully.
     *         If the user is found and the item is deleted successfully, HTTP status NO CONTENT is returned.
     *         If the user is not found, HTTP status NOT FOUND is returned.
     *         If the shopping item is not found, HTTP status NOT FOUND is returned.
     */
    @DeleteMapping("/{itemName}")
    public ResponseEntity<Void> deleteShoppingItemFromUser(@PathVariable ObjectId userId, @PathVariable String itemName) {
        try {
            shoppingItemService.deleteShoppingItemFromUser(userId, itemName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ShoppingItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
