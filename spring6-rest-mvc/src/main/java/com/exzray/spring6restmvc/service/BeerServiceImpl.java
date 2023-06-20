package com.exzray.spring6restmvc.service;

import com.exzray.spring6restmvc.component.handler.NotFoundException;
import com.exzray.spring6restmvc.data.BeerDTO;
import com.exzray.spring6restmvc.domain.beer.Beer;
import com.exzray.spring6restmvc.domain.beer.BeerRepository;
import com.exzray.spring6restmvc.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public Page<BeerDTO> getBeers(String beerName, Integer offset, Integer size) {
        Page<Beer> page;

        PageRequest pageRequest = buildPageRequest(offset, size);

        if(StringUtils.hasText(beerName)){
            page = getBeerAdv(beerName);
        } else {
            page = beerRepository.findAll(pageRequest);
        }

        return page.map(beerMapper::to);

//        return new PageImpl<>(list
//                .stream()
//                .map(beerMapper::to)
//                .collect(Collectors.toList()));
    }

    @Override
    public BeerDTO getBeer(UUID id) {
        log.debug("Beer ID been called inn service: {}", id);
        Optional<BeerDTO> result = beerRepository.findById(id).map(beerMapper::to);
        return result.orElseThrow(NotFoundException::new);
    }

    @Override
    public BeerDTO createBeer(BeerDTO request) {
        Beer beer = beerMapper.from(request);
        return beerMapper.to(beerRepository.save(beer));
    }

    @Override
    public void updateBeer(UUID beerID, BeerDTO beerDTO) {
        AtomicReference<Optional<BeerDTO>> reference = new AtomicReference<>();

        beerRepository.findById(beerID).ifPresentOrElse(beer -> {
            beer.setBeerName(beer.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyle());
            reference.set(Optional.of(beerMapper.to(beerRepository.save(beer))));
        }, () -> {
            reference.set(Optional.empty());
        });

        reference.get();
    }

    @Override
    public void deleteBeer(UUID beerID) {
        beerRepository.deleteById(beerID);
    }

    private Page<Beer> getBeerAdv(String beerName) {
        return beerRepository.findAllByBeerNameContainsIgnoreCase(beerName, null);
    }

    private PageRequest buildPageRequest(Integer offset, Integer size){
        int queryPageNumber;
        int queryPageSize;

        if (offset != null && offset > 0)
            queryPageNumber = offset - 1;
        else
            queryPageNumber = DEFAULT_PAGE;

        if (size == null)
            queryPageSize = DEFAULT_PAGE_SIZE;
        else
            queryPageSize = size;

        Sort sort = Sort.by(Sort.Order.asc("beerName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
