package com.ske.library.dbSeeders;

import com.ske.library.cart.domain.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class CartSeeder implements ISeeder {

    CartRepository cartRepository;

    @Override
    public void seed() {

    }

    @Override
    public void resetDb() {
        log.info("Reseting all db.");
        cartRepository.deleteAll();
        log.info("End reseting db.");

    }
}
