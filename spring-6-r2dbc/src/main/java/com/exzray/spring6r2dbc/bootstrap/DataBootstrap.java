package com.exzray.spring6r2dbc.bootstrap;

import com.exzray.spring6r2dbc.domain.Beer;
import com.exzray.spring6r2dbc.domain.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadTestData();
    }

    private void loadTestData() {
        beerRepository
                .count()
                .doOnSuccess(count -> log.info("loadTestData() -> count before: {}", count))
                .flatMap(count -> {
                    if (count == 0) {
                        Beer beer1 = Beer
                                .builder()
                                .beerName("Beer 1")
                                .beerStyle("IPA")
                                .price(BigDecimal.TEN)
                                .quantityOnHand(10)
                                .upc("ABC001")
                                .build();

                        Beer beer2 = Beer
                                .builder()
                                .beerName("Beer 2")
                                .beerStyle("IPA")
                                .price(BigDecimal.TEN)
                                .quantityOnHand(10)
                                .upc("ABC002")
                                .build();

                        Beer beer3 = Beer
                                .builder()
                                .beerName("Beer 3")
                                .beerStyle("IPA")
                                .price(BigDecimal.TEN)
                                .quantityOnHand(10)
                                .upc("ABC003")
                                .build();

                        return beerRepository.saveAll(List.of(beer1, beer2, beer3)).then(beerRepository.count());

                    } else {
                        return Mono.just(0);
                    }
                })
                .subscribe(count -> log.info("loadTestData() -> count after: {}", count));
    }
}
