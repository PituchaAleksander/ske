package com.ske.library.order.application.controller;

import com.ske.library.cart.application.response.CartDto;
import com.ske.library.cart.domain.service.CartService;
import com.ske.library.order.application.response.OrderDto;
import com.ske.library.order.domain.service.OrderService;
import com.ske.library.payu.config.PayUConfigurationProperties;
import com.ske.library.payu.model.*;
import com.ske.library.payu.service.PayUOrderService;
import com.ske.library.securityJwt.domain.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.ske.library.payu.model.OrderCreateResponse.Status.STATUS_CODE_SUCCESS;

@Slf4j
@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final PayUOrderService payuOrderService;
    private final PayUConfigurationProperties payUConfiguration;
    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping()
    public String handleCheckout(HttpServletRequest request) {
        PayUForm payUForm = new PayUForm();
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        payUForm.setEmail(user.getEmail());
        payUForm.setProductName("Books");
        CartDto cart = cartService.getCart(user.getUserUUID());
        payUForm.setProductPrice((int) cart.getPrice());

        log.info(payUForm.toString());

        orderService.saveOrder(user.getUserUUID(), cart);
        cartService.clearCart(user.getUserUUID());

        final OrderCreateRequest orderRequest = prepareOrderCreateRequest(payUForm, request);

        log.info("Order request = {}", orderRequest);

        final OrderCreateResponse orderResponse = payuOrderService.order(orderRequest);

        if (!orderResponse.getStatus().getStatusCode().equals(STATUS_CODE_SUCCESS)) {
            throw new RuntimeException("Payment failed! ");
        }

        return orderResponse.getRedirectUri();
    }

    private OrderCreateRequest prepareOrderCreateRequest(final PayUForm payUForm, final HttpServletRequest request) {
        return OrderCreateRequest.builder()
                .customerIp(request.getRemoteAddr())
                .merchantPosId(payUConfiguration.getMerchantPosId())
                .description(payUConfiguration.getDescription())
                .currencyCode("PLN")
                .totalAmount(
                        Objects.nonNull(payUForm.getProductPrice())
                                ? String.valueOf(100 * payUForm.getProductPrice())
                                : "2500"
                ).products(
                        Collections.singletonList(
                                Product.builder()
                                        .name(
                                                StringUtils.isNotBlank(payUForm.getProductName())
                                                        ? payUForm.getProductName()
                                                        : "Test product name"
                                        ).quantity("1")
                                        .unitPrice(
                                                Objects.nonNull(payUForm.getProductPrice())
                                                        ? String.valueOf(100 * payUForm.getProductPrice())
                                                        : "2500"
                                        ).build()
                        )).buyer(
                        Buyer.builder()
                                .email(payUForm.getEmail())
                                .language("pl")
                                .build()
                ).build();
    }

    @GetMapping()
    public List<OrderDto> getUserOrders() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getOrders(user.getUserUUID());
    }

    @GetMapping("/user/{userId}")
    public List<OrderDto> getUserOrdersParam(@RequestParam String userId) {
        return orderService.getOrders(userId);
    }

    @PutMapping("/accept/{userId}")
    public OrderDto acceptOrder(@RequestParam String orderId) {
        return orderService.acceptOrder(orderId);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deletetOrder(@RequestParam String orderId) {
        return orderService.removeOrder(orderId);
    }
}
