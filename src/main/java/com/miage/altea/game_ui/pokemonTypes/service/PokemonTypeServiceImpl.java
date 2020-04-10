package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;

import java.util.List;
import java.util.Objects;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private String url;
    private RestTemplate template;
    private CircuitBreaker circuitBreaker;
    private Retry retry;

    @Override
    public List<PokemonType> listPokemonsTypes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, String.valueOf(LocaleContextHolder.getLocale()));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<PokemonType[]> response = this.template.exchange(this.url + "/pokemon-types/", HttpMethod.GET, entity, PokemonType[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    @Override
    @Cacheable("pokemon-types")
    public PokemonType getPokemonType(int id) {
        return this.retry
                .executeSupplier(() -> template.getForObject(url +"/pokemon-types/{id}", PokemonType.class, id));
    }

    @Autowired
    @Qualifier("restTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        template = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        url= pokemonServiceUrl;
    }

    @Autowired
    public void setCircuitBreaker(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Autowired
    public void setRetry(Retry retry) {
        this.retry = retry;
    }
}