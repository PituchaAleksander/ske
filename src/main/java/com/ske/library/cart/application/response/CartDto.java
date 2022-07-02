package com.ske.library.cart.application.response;

import com.ske.library.cart.domain.CartBook;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String id;

    private List<CartBook> books;

    private float price;
}
