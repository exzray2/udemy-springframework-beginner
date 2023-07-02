package com.exzray.spring6reactive.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class PersonRepositoryImpl implements PersonRepository {
    Person jack = Person
            .builder()
            .id(1)
            .firstName("Jack")
            .lastName("Obito")
            .build();

    Person fiona = Person
            .builder()
            .id(2)
            .firstName("Fiona")
            .lastName("Christ")
            .build();

    Person sam = Person
            .builder()
            .id(3)
            .firstName("Serious")
            .lastName("Sam")
            .build();

    Person jesse = Person
            .builder()
            .id(4)
            .firstName("Jesse")
            .lastName("Mary")
            .build();

    @Override
    public Mono<Person> getById(Integer id) {
        return findAll()
                .filter(person -> Objects.equals(person.getId(), id))
                .next();
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(jack, fiona, sam, jesse);
    }
}
