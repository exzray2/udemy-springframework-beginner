package com.exzray.spring6r2dbc.service;

import com.exzray.spring6r2dbc.domain.BeerRepository;
import com.exzray.spring6r2dbc.dto.BeerDTO;
import com.exzray.spring6r2dbc.dto.BeerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> getBeer() {
        return beerRepository
                .findAll()
                .map(beerMapper::to);
    }

    @Override
    public Mono<BeerDTO> getBeer(Integer beerId) {
        return beerRepository
                .findById(beerId)
                .map(beerMapper::to);
    }

    @Override
    public Mono<BeerDTO> createBeer(BeerDTO body) {
        return beerRepository
                .save(beerMapper.from(body))
                .map(beerMapper::to);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerID, BeerDTO body) {
        return beerRepository
                .findById(beerID)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(beer -> {
                    beer.setBeerName(body.beerName());
                    beer.setBeerStyle(body.beerStyle());
                    beer.setUpc(body.upc());
                    beer.setPrice(body.price());
                    beer.setQuantityOnHand(body.quantityOnHand());

                    return beer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::to);
    }

    @Override
    public Mono<Void> deleteBeer(Integer beerID) {
        return beerRepository
                .deleteById(beerID);
    }
}
