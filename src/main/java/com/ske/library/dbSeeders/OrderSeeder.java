package com.ske.library.dbSeeders;

import com.ske.library.order.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class OrderSeeder implements ISeeder {

    OrderRepository orderRepository;

    @Override
    public void seed() {

    }

    @Override
    public void resetDb() {
        log.info("Reseting all db.");
        orderRepository.deleteAll();
        log.info("End reseting db.");

    }
}
