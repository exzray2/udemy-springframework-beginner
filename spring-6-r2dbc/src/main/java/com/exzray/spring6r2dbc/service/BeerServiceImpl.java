package com.exzray.spring6r2dbc.service;

import com.exzray.spring6r2dbc.domain.BeerRepository;
import com.exzray.spring6r2dbc.dto.BeerDTO;
import com.exzray.spring6r2dbc.dto.BeerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeer() {
        return beerRepository
                .findAll()
                .map(beerMapper::to);
    }
}
