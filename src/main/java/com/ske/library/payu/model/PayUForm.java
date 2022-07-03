package com.ske.library.payu.model;


import lombok.Data;

@Data
public class PayUForm {
    private String email;
    private Integer productPrice;
    private String productName;
}