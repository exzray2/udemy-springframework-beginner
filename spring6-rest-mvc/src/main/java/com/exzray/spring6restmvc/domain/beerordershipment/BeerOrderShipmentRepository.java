package com.exzray.spring6restmvc.domain.beerordershipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderShipmentRepository extends JpaRepository<BeerOrderShipment, UUID> {
}
