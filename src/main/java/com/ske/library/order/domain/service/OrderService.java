package com.ske.library.order.domain.service;

import com.ske.library.cart.application.response.CartDto;
import com.ske.library.order.application.response.OrderDto;
import com.ske.library.order.domain.Order;
import com.ske.library.order.domain.repository.OrderRepository;
import com.ske.library.securityJwt.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final OrderRepository orderRepository;
    final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    public OrderDto saveOrder(String userUUID, CartDto cartDto){
        Order o = new Order(userUUID, cartDto.getBooks(), cartDto.getPrice());

        return orderRepository.save(o).toDto();
    }

    public List<OrderDto> getOrders(){
        List<Order> list = orderRepository.findAll();

        return list.stream().map(o -> o.toDto()).toList();
    }

    public List<OrderDto> getOrders(String userUUID){
        List<Order> list = orderRepository.findAllByUserUUID(userUUID);

        return list.stream().map(o -> o.toDto()).toList();
    }

    public OrderDto acceptOrder(String orderId){
        Optional<Order> o = orderRepository.findById(orderId);
        o.get().setAccept(true);

        return o.get().toDto();
    }

    public ResponseEntity<?> removeOrder(String orderId){
        orderRepository.deleteById(orderId);
        return ResponseEntity.ok("OK");
    }

}
