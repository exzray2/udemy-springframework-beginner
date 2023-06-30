package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerPageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {
    private final RestTemplateBuilder restTemplateBuilder;

    private final static String BASE_URL = "http://localhost:8080";

    private final static String BASE_BEER_URL = "/api/v1/beer";

    @Override
    public Page<BeerDTO> getBeer() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<BeerPageImpl> response = restTemplate.getForEntity(BASE_URL + BASE_BEER_URL, BeerPageImpl.class);
        log.info("received string: {}", response);

        return null;
    }
}
