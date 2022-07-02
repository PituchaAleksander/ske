package com.ske.library.cart.domain;

import com.ske.library.generics.MongoModel;
import com.ske.library.cart.application.response.CartDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class Cart implements MongoModel {

    @Id
    private String id;

    private String userUUID;

    private List<CartBook> books;

    private float price;

    public Cart(String userUUID, List<CartBook> books, float price) {
        this.books = books;
        this.userUUID = userUUID;
        this.price = price;
    }

    public CartDto toDto(){
        return new CartDto(this.id, this.getBooks(), this.price);
    }
}