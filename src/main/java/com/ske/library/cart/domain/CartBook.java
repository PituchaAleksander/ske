package com.ske.library.cart.domain;

import com.ske.library.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartBook {
    private Book book;
    private Integer count;
}
