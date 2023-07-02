package com.exzray.spring6r2dbc.dto;

import com.exzray.spring6r2dbc.domain.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer from(BeerDTO source);

    BeerDTO to(Beer source);
}
