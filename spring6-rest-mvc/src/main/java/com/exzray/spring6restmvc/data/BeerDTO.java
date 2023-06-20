package com.exzray.spring6restmvc.data;

import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerDTO {
    @Null
    private UUID id;

    @Null
    private Integer version;

    private String beerName;

    private BeerStyle beerStyle;

    private String upc;

    private Integer quantityOnHand;

    private BigDecimal price;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
