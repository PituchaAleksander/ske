package com.ske.library.cart.domain.repository;

import com.ske.library.cart.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserUUID(String userUUID);
    void deleteByUserUUID(String userUUID);
}
