package com.ske.library.book.domain.repository;

import com.ske.library.book.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends MongoRepository<Book, String> {
}
