package com.ske.library.order.application.response;

import com.ske.library.cart.domain.CartBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAdminDto {

    private String id;

    private List<CartBook> books;

    private float price;

    private boolean accept;

    private String userUUID;

    private String userName;
}
