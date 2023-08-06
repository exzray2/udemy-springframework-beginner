package com.exzray.reactivemongo.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record BeerDTO(
        Integer id,
        String beerName,
        String beerStyle,
        String upc,
        Integer quantityOnHand,
        BigDecimal price,
        LocalDateTime createdOn,
        LocalDateTime updatedOn) {
}
