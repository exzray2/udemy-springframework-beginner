package com.exzray.spring6r2dbc.controller;

import com.exzray.spring6r2dbc.dto.BeerDTO;
import com.exzray.spring6r2dbc.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";

    public static final String BEER_PATH_ID = BEER_PATH + "/{beerID}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> getBeer() {
        return beerService.getBeer();
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeer(@PathVariable Integer beerID) {
        return beerService.getBeer(beerID);
    }

    @PostMapping(BEER_PATH)
    public Mono<ResponseEntity<Void>> createBeer(@RequestBody BeerDTO body) {
        return beerService
                .createBeer(body)
                .map(beerDTO -> ResponseEntity
                        .created(
                                UriComponentsBuilder
                                        .fromHttpUrl("http://localhost:8080/" + BEER_PATH + "/" + beerDTO.id())
                                        .build().toUri())
                        .build());
    }

    @PutMapping(BEER_PATH_ID)
    public Mono<ResponseEntity<BeerDTO>> updateBeer(@PathVariable Integer beerID, @RequestBody BeerDTO body){
        return beerService
                .updateBeer(beerID, body)
                .map(beerDTO -> ResponseEntity.status(HttpStatus.OK).body(beerDTO));
    }

    @DeleteMapping(BEER_PATH_ID)
    public Mono<ResponseEntity<Void>> deleteBeer(@PathVariable Integer beerID){
        return beerService
                .deleteBeer(beerID)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));
    }
}
