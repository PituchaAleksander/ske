package com.ske.library.book.domain.service;

import com.ske.library.book.application.request.BookRequest;
import com.ske.library.book.application.response.BookDto;
import com.ske.library.book.domain.Book;
import com.ske.library.book.domain.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BookService {

    final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookRequest request){
        return bookRepository.save(new Book(request.getTitle(), request.getAuthor(), request.getPrice())).toDto();
    }

    public List<BookDto> getBooks(){
        return bookRepository.findAll().stream().map(b -> b.toDto()).toList();
    }

    public BookDto getBook(String id){
        return bookRepository.findById(id).get().toDto();
    }

    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(BookRequest request, String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException());

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());

        return bookRepository.save(book).toDto();
    }
}
