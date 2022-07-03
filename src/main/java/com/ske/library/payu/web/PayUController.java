package com.ske.library.payu.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PayUController {

    public static final String URL_PAYMENT_CALLBACK = "/payu-callback";

    @GetMapping(URL_PAYMENT_CALLBACK)
    public HttpServletRequest handlePaymentCallback(final @RequestParam Optional<String> error, HttpServletRequest request) {
//        model.addAttribute("hasError", error.isPresent());
//        model.addAttribute("paymentFinished", true);
//
        error.ifPresent(e ->  log.info(e));

        return request;
    }
}
