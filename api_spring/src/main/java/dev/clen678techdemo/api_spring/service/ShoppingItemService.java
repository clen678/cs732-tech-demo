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

/**
 * ShoppingItemService class handles business logic related to shopping item operations.
 * It provides methods for retrieving, adding, and deleting shopping items for a specific user.
 */
@Service
public class ShoppingItemService {

    @Autowired
    private UserRepository userRepository; 
    
    /**
     * Retrieves a list of all shopping items for a specific user.
     * 
     * @param userId The ID of the user whose shopping items are to be retrieved.
     * @return List<ShoppingItem> containing all shopping items for the specified user.
     *         If the user is not found, a UserNotFoundException is thrown.
     * @throws UserNotFoundException if the user is not found in the database.
     */
    public List<ShoppingItem> getAllShoppingItemsForUser(ObjectId userId) {

        // find user
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        List<ShoppingItem> shoppingItems = user.get().getShoppingItems();

        return shoppingItems;
    }

    /**
     * Adds a new shopping item to the user's shopping list.
     * 
     * @param userId The ID of the user to whom the shopping item is to be added.
     * @param shoppingItem The new shopping item to be added.
     * @return ShoppingItem The added shopping item.
     *         If the user is not found, a UserNotFoundException is thrown.
     *         If the shopping item already exists for the user, a ShoppingItemConflictException is thrown.
     * @throws UserNotFoundException if the user is not found in the database.
     * @throws ShoppingItemConflictException if the shopping item already exists for the user.
     */
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

    /**
     * Deletes a shopping item from the user's shopping list.
     * 
     * @param userId The ID of the user from whom the shopping item is to be deleted.
     * @param itemName The name of the shopping item to be deleted.
     * @throws UserNotFoundException if the user is not found in the database.
     * @throws ShoppingItemNotFoundException if the shopping item is not found for the user.
     */
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
