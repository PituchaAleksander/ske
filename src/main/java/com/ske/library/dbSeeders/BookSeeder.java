package com.ske.library.dbSeeders;

import com.github.javafaker.Faker;
import com.ske.library.book.domain.Book;
import com.ske.library.book.domain.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
@AllArgsConstructor
public class BookSeeder implements ISeeder {

    BookRepository bookRepository;

    @Override
    public void seed() {
        List<Book> books = new ArrayList<>();

        books.add(new Book("Lalka", "Bolesław Prus", 30));
        books.add(new Book("Dziady", "Adam Mickiewicz", 45));
        books.add(new Book("Pan Tadeusz", "Adam Mickiewicz", 35));
        books.add(new Book("Kamizelka", "Bolesław Prus", 50));

        for (var book : books) {
            bookRepository.save(book);
        }
    }

    @Override
    public void resetDb() {
        log.info("Reseting all db.");
        bookRepository.deleteAll();
        log.info("End reseting db.");

    }
}
