package com.ske.library.book.application.controller;

import com.ske.library.book.application.request.BookRequest;
import com.ske.library.book.application.response.BookDto;
import com.ske.library.book.domain.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {

    BookService bookService;

    @GetMapping()
    public List<BookDto> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    public BookDto getBook(@PathVariable String id){
        return bookService.getBook(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id){
        bookService.deleteBook(id);

        return new ResponseEntity<>("Book deleted successfully.", HttpStatus.OK);
    }

    @PostMapping()
    public BookDto addBook(@RequestBody BookRequest request){
        return bookService.addBook(request);
    }

    @PutMapping("{id}")
    public BookDto updateBook(@RequestBody BookRequest request, @PathVariable String id){
        return bookService.updateBook(request, id);
    }
}
