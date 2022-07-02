package com.ske.library.book.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private float price;
}
