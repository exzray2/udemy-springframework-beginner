package com.exzray.spring6restmvc.service;

import com.exzray.spring6restmvc.data.BeerDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerService {
    Page<BeerDTO> getBeers(String beerName, Integer offset, Integer size);

    BeerDTO getBeer(UUID id);

    BeerDTO createBeer(BeerDTO request);

    void updateBeer(UUID beerID, BeerDTO beerDTO);

    void deleteBeer(UUID beerID);
}
