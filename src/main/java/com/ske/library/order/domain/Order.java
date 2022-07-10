package com.ske.library.order.domain;

import com.ske.library.cart.domain.CartBook;
import com.ske.library.order.application.response.OrderAdminDto;
import com.ske.library.order.application.response.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private String id;

    private String userUUID;

    private List<CartBook> books;

    private float price;

    private boolean accept;

    public Order(String userUUID, List<CartBook> books, float price) {
        this.books = books;
        this.userUUID = userUUID;
        this.price = price;
        this.accept = false;
    }

    public OrderDto toDto(){
        return new OrderDto(this.id, this.getBooks(), this.price, this.accept);
    }

    public OrderAdminDto toAdminDto(String userName){
        return new OrderAdminDto(this.id, this.getBooks(), this.price, this.accept, this.userUUID, userName);
    }
}