package com.exzray.spring6restmvc.domain.category;

import com.exzray.spring6restmvc.domain.beer.Beer;
import com.exzray.spring6restmvc.domain.beer.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BeerRepository beerRepository;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional
    @Test
    void testAddCategory(){
        Category savedCategory = categoryRepository
                .save(Category
                        .builder()
                        .description("Ales")
                        .build());

        testBeer.addCategory(savedCategory);

        Beer savedBeer = beerRepository.save(testBeer);

        System.out.println(savedBeer);
    }
}