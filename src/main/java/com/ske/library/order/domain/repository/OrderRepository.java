package com.ske.library.order.domain.repository;

import com.ske.library.order.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByUserUUID(String userUUID);
}
