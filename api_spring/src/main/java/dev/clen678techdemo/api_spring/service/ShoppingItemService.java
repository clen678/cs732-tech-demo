package dev.clen678techdemo.api_spring.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.clen678techdemo.api_spring.repositories.UserRepository;
import dev.clen678techdemo.api_spring.domain.ShoppingItem;
import dev.clen678techdemo.api_spring.domain.User;
import dev.clen678techdemo.api_spring.exceptions.ShoppingItemConflictException;
import dev.clen678techdemo.api_spring.exceptions.ShoppingItemNotFoundException;
import dev.clen678techdemo.api_spring.exceptions.UserNotFoundException;


@Service
public class ShoppingItemService {

    @Autowired
    private UserRepository userRepository; 
    
    public List<ShoppingItem> getAllShoppingItemsForUser(ObjectId userId) {

        // find user
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        List<ShoppingItem> shoppingItems = user.get().getShoppingItems();

        return shoppingItems;
    }

    public ShoppingItem addShoppingItemToUser(ObjectId userId, ShoppingItem shoppingItem) {
        
        // find user
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        // validate unique items
        List<ShoppingItem> shoppingItems = user.get().getShoppingItems();
        if (shoppingItems.stream().anyMatch(item -> item.getName().equals(shoppingItem.getName()))) {
            throw new ShoppingItemConflictException("Username already exists");
        }

        user.get().getShoppingItems().add(shoppingItem);
        userRepository.save(user.get());
        return shoppingItem;
    }

    public void deleteShoppingItemFromUser(ObjectId userId, String itemName) {
        
        // find user
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        // find shopping item for user
        List<ShoppingItem> shoppingItems = user.get().getShoppingItems();
        Optional<ShoppingItem> itemToDelete = shoppingItems.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();

        if (!itemToDelete.isPresent()) {
            throw new ShoppingItemNotFoundException("Shopping item not found");
        } 

        shoppingItems.remove(itemToDelete.get());
        userRepository.save(user.get());
    }
}
