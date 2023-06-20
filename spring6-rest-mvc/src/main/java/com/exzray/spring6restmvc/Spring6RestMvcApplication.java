package com.exzray.spring6restmvc;

import com.exzray.spring6restmvc.data.BeerCSVRecord;
import com.exzray.spring6restmvc.data.BeerDTO;
import com.exzray.spring6restmvc.service.BeerService;
import com.exzray.spring6restmvc.service.CSVService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.math.BigDecimal;

@SpringBootApplication
public class Spring6RestMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring6RestMvcApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(CSVService csvService, BeerService beerService) {
		return args -> {
			if (beerService.getBeers(null, 1, 25).getContent().size() == 0){
				File file = ResourceUtils.getFile("classpath:data/hot.csv");
				for (BeerCSVRecord record : csvService.convertCSV(file)){
					BeerDTO beerDTO = BeerDTO
							.builder()
							.beerName(record.getName())
							.quantityOnHand(record.getQuantity())
							.price(new BigDecimal(record.getPrice().toString()))
							.build();

					beerService.createBeer(beerDTO);
				}
			}
		};
	}
}
