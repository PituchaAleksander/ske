package com.ske.library.payu.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PayUController {

    public static final String URL_PAYMENT_CALLBACK = "/payu-callback";

    @GetMapping(URL_PAYMENT_CALLBACK)
    public HttpStatus handlePaymentCallback(final @RequestParam Optional<String> error, Model model) {
        model.addAttribute("hasError", error.isPresent());
        model.addAttribute("paymentFinished", true);

        if(error.isPresent()){
            error.ifPresent(e ->  log.info(e));
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }else{
            return HttpStatus.OK;
        }
    }
}
