package dev.clen678techdemo.api_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.clen678techdemo.api_spring.repositories.ShoppingItemRepository;


@Service
public class ShoppingItemService {

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;
    
}
