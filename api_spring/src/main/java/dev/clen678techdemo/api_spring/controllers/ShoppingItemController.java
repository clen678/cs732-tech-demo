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

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/api/users/{userId}/items")
public class ShoppingItemController {
    
    @Autowired
    private ShoppingItemService shoppingItemService;

    @GetMapping
    public ResponseEntity<List<ShoppingItem>> getAllShoppingItemsForUser(@PathVariable ObjectId userId) throws UserNotFoundException {
        try {
            return new ResponseEntity<List<ShoppingItem>>(shoppingItemService.getAllShoppingItemsForUser(userId), HttpStatus.OK);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ShoppingItem> addShoppingItemToUser(@PathVariable ObjectId userId, @RequestBody ShoppingItem newItem) throws UserNotFoundException, ShoppingItemConflictException {
        try {
            return new ResponseEntity<ShoppingItem>(shoppingItemService.addShoppingItemToUser(userId, newItem), HttpStatus.CREATED);
            
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ShoppingItemConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{itemName}")
    public ResponseEntity<Void> deleteShoppingItemFromUser(@PathVariable ObjectId userId, @PathVariable String itemName) throws UserNotFoundException, ShoppingItemNotFoundException {
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
