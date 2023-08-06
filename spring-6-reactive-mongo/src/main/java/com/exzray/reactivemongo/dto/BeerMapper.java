package com.exzray.reactivemongo.dto;

import com.exzray.reactivemongo.domain.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer from(BeerDTO source);

    BeerDTO to(Beer source);
}
