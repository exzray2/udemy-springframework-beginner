package com.exzray.spring6restmvc.mapper;

import com.exzray.spring6restmvc.data.BeerDTO;
import com.exzray.spring6restmvc.domain.beer.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer from(BeerDTO dto);

    BeerDTO to(Beer beer);

}
