package com.ske.library.cart.application.controller;

import com.ske.library.cart.application.response.CartDto;
import com.ske.library.cart.domain.service.CartService;
import com.ske.library.securityJwt.domain.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/cart")
@AllArgsConstructor
public class CartController {

    CartService cartService;

    @GetMapping()
    public CartDto getCart(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.getCart(user.getUserUUID());
    }

    @PostMapping("{id}")
    public CartDto addBook(@PathVariable String id){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.addBookToCart(id, user.getUserUUID());
    }

    @DeleteMapping()
    public ResponseEntity<?> clearCart(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.clearCart(user.getUserUUID());
    }

    @PutMapping("{id}")
    public CartDto decrementBook(@PathVariable String id){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.removeBookToCart(id, user.getUserUUID());
    }
}
