package com.exzray.spring6restmvc.controller;

import com.exzray.spring6restmvc.data.BeerDTO;
import com.exzray.spring6restmvc.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;

    @GetMapping("")
    public ResponseEntity<?> getBeers(
            @RequestParam(name = "name", required = false) String beerName,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false)Integer size) {
        return ResponseEntity.ok(beerService.getBeers(beerName, offset, size));
    }

    @GetMapping("/{id}")
    public BeerDTO getBeer(@PathVariable UUID id) {
        log.debug("Beer ID been called in controller: {}", id);

        return beerService.getBeer(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createBeer(@Validated @RequestBody BeerDTO request) {
        BeerDTO beerDTO = beerService.createBeer(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDTO.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(beerDTO);
    }

    @PutMapping("/{beerID}")
    public ResponseEntity<?> updateBeer(@PathVariable UUID beerID, @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(beerID, beerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{beerID}")
    public ResponseEntity<?> deleteBeer(@PathVariable UUID beerID) {
        beerService.deleteBeer(beerID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
