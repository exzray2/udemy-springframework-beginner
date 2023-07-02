package com.exzray.spring6r2dbc.domain;

import com.exzray.spring6r2dbc.configuration.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveNewBeer() {
        beerRepository
                .save(getTestBeer())
                .subscribe(beer -> log.info("received: {}", beer));
    }

    Beer getTestBeer(){
        return Beer
                .builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(10)
                .upc("ABC123")
                .build();
    }
}