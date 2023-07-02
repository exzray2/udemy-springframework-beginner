package com.exzray.spring6reactive.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PersonRepositoryImplTest {
    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoById() {
        Mono<Person> personMono = personRepository.getById(1);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.subscribe(person -> log.info("received: {}", person));
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.subscribe(person -> {
            log.info("received: {}", person);
        });
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono
                .map(Person::getFirstName)
                .subscribe(s -> log.info("received firstName: {}", s));
    }

    @Test
    void testFluxBlock() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person = personFlux.blockFirst();

        log.info("received: {}", person);
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.subscribe(person -> {
            log.info("received: {}", person);
        });
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux
                .map(Person::getFirstName)
                .subscribe(s -> log.info("received firstName: {}", s));
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();

        Mono<List<Person>> listMono = personFlux.collectList();

        listMono.subscribe(list -> {
            list
                    .forEach(person -> log.info(person.getFirstName()));
        });
    }

    @Test
    void testFilterOnName() {
        personRepository
                .findAll()
                .filter(person -> person.getFirstName().equalsIgnoreCase("fiona"))
                .subscribe(person -> log.info("received: {}", person));
    }

    @Test
    void testGetById() {
        Mono<Person> personMono = personRepository
                .findAll()
                .filter(person -> person.getFirstName().equalsIgnoreCase("fiona"))
                .next();

        personMono.subscribe(person -> log.info("received: {}", person));
    }

    @Test
    void testFindPersonByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer id = 8;

        Mono<Person> personMono = personFlux.filter(person -> Objects.equals(person.getId(), id)).single();

        personMono.subscribe(person -> log.info("received: {}", person));
    }
}