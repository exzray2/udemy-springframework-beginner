package com.exzray.spring6r2dbc.service;

import com.exzray.spring6r2dbc.dto.BeerDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Flux<BeerDTO> getBeer();

    Mono<BeerDTO> getBeer(Integer beerId);

    Mono<BeerDTO> createBeer(BeerDTO body);

    Mono<BeerDTO> updateBeer(Integer beerID, BeerDTO body);

    Mono<Void> deleteBeer(Integer beerID);
}
