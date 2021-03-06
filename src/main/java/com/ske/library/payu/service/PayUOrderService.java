package com.ske.library.payu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ske.library.payu.config.PayUConfigurationProperties;
import com.ske.library.payu.model.OrderCreateRequest;
import com.ske.library.payu.model.OrderCreateResponse;
import com.ske.library.payu.web.PayUController;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ngrok.api.NgrokApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayUOrderService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final PayUConfigurationProperties payUConfiguration;

  @Autowired(required = false)
  private NgrokApiClient ngrok;

  @Resource(name = "payuApiRestTemplate")
  private RestTemplate restTemplate;

  @Value("${server.addr}")
  private String serverAddress;

  @Value("${ngrok.enabled}")
  private boolean ngrokEnabled;

  @SneakyThrows
  public OrderCreateResponse order(final OrderCreateRequest orderCreateRequest) {
    orderCreateRequest.setContinueUrl(chooseCallbackUrl());

    final ResponseEntity<String> jsonResponse = restTemplate.postForEntity(payUConfiguration.getOrderUrl(), orderCreateRequest, String.class);

    log.info("Response as String = {}", jsonResponse.getBody());

    return objectMapper.readValue(jsonResponse.getBody(), OrderCreateResponse.class);
  }

  /*
   * if running with ngrok, proper host will be used so local development/testing is easy :)
   */
  private String chooseCallbackUrl() {
    return (ngrokEnabled ? ngrok.getHttpsTunnelUrl() : serverAddress) + PayUController.URL_PAYMENT_CALLBACK;
  }
}
