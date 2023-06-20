package com.exzray.spring6restmvc.domain.beerorder;

import com.exzray.spring6restmvc.domain.beer.Beer;
import com.exzray.spring6restmvc.domain.beer.BeerRepository;
import com.exzray.spring6restmvc.domain.beerordershipment.BeerOrderShipment;
import com.exzray.spring6restmvc.domain.customer.Customer;
import com.exzray.spring6restmvc.domain.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerOrderTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    Customer testCustomer;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = Customer
                .builder()
                .name("Test User")
                .email("testuser@gmail.com")
                .build();

        testCustomer = customerRepository.save(testCustomer);

        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder
                .builder()
                .customerRef("Test Order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment
                        .builder()
                        .trackingNumber("ABC123")
                        .build())
                .build();

        BeerOrder saved = beerOrderRepository.save(beerOrder);

        System.out.println(saved.getCustomer().getEmail());
    }
}