package com.ske.library.cart.application.request;

import com.ske.library.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CartRequest {
    private String userUUID;
    private List<Book> book;
}
