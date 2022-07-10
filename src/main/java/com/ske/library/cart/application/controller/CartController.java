package com.ske.library.cart.application.controller;

import com.ske.library.cart.application.response.CartDto;
import com.ske.library.cart.domain.service.CartService;
import com.ske.library.securityJwt.domain.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/cart")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
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
    public HttpStatus clearCart(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.clearCart(user.getUserUUID());
    }

    @DeleteMapping("{id}")
    public CartDto decrementBook(@PathVariable String id){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cartService.removeBookToCart(id, user.getUserUUID());
    }
}
