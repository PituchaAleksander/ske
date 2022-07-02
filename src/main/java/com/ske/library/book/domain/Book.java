package com.ske.library.book.domain;

import com.ske.library.book.application.response.BookDto;
import com.ske.library.generics.MongoModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Book implements MongoModel {

    @Id
    private String id;

    private String title;

    private String author;

    private float price;

    public Book(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public BookDto toDto(){
        return new BookDto(this.id, this.title, this.author, this.price);
    }
}