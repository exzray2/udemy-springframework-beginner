package com.exzray.spring6r2dbc.service;

import com.exzray.spring6r2dbc.dto.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {
    Flux<BeerDTO> listBeer();
}
