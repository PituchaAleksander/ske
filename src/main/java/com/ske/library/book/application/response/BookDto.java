package com.ske.library.book.application.response;

import com.ske.library.generics.MongoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto implements MongoDto {
    private String id;

    private String title;

    private String author;

    private float price;

}