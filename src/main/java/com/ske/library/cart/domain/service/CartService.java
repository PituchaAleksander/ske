package com.ske.library.cart.domain.service;

import com.ske.library.book.domain.Book;
import com.ske.library.book.domain.repository.BookRepository;
import com.ske.library.cart.application.response.CartDto;
import com.ske.library.cart.domain.Cart;
import com.ske.library.cart.domain.CartBook;
import com.ske.library.cart.domain.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CartService {

    final CartRepository cartRepository;

    final BookRepository bookRepository;

    @Autowired
    public CartService(CartRepository cartRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
    }

    public CartDto getCart(String userUUID){
        Optional<Cart> o = cartRepository.findByUserUUID(userUUID);

        if(o.isPresent()) {
            return o.get().toDto();
        }else{
            return new CartDto();
        }
    }

    public CartDto addBookToCart(String id, String userUUID){
        Book book = bookRepository.findById(id).get();

        Optional<Cart> o = cartRepository.findByUserUUID(userUUID);

        if(o.isPresent()){
            List<CartBook> cartBooks = o.get().getBooks().stream().filter(item -> item.getBook().equals(book)).collect(Collectors.toList());
            if(cartBooks.size()>0){
                CartBook cartBook = cartBooks.get(0);
                cartBook.setCount(cartBook.getCount() + 1);
                o.get().setPrice(o.get().getPrice() + book.getPrice());
                return cartRepository.save(o.get()).toDto();
            }else{
                CartBook cartBook = new CartBook(book, 1);
                o.get().setPrice(o.get().getPrice() + book.getPrice());
                o.get().getBooks().add(cartBook);
                return cartRepository.save(o.get()).toDto();
            }

        }else{
            Cart cart = new Cart(userUUID, Arrays.asList(new CartBook(book, 1)), book.getPrice());
            return cartRepository.save(cart).toDto();
        }
    }

    public HttpStatus clearCart(String userUUID){
        cartRepository.deleteByUserUUID(userUUID);
        return HttpStatus.OK;
    }

    public CartDto removeBookToCart(String id, String userUUID){
        Book book = bookRepository.findById(id).get();

        Optional<Cart> o = cartRepository.findByUserUUID(userUUID);

        if(o.isPresent()){
            CartBook cartBook = o.get().getBooks().stream().filter(item -> item.getBook().equals(book))
                    .collect(Collectors.toList()).get(0);
            if(cartBook.getCount() == 1){
                o.get().getBooks().remove(cartBook);
            }else{
                cartBook.setCount(cartBook.getCount() - 1);
            }
            o.get().setPrice(o.get().getPrice() - book.getPrice());
            return cartRepository.save(o.get()).toDto();
        }else{
            return cartRepository.save(new Cart(userUUID, null, 0)).toDto();
        }
    }
}
