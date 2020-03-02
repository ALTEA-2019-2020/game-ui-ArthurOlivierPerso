package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private String url;
    private RestTemplate template;

    public List<PokemonType> listPokemonsTypes() {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, String.valueOf(LocaleContextHolder.getLocale()));
        var pokemonTypes = template.getForObject(
                url+"/pokemon-types/",
                PokemonType[].class,
                headers
        );
        return List.of(Objects.requireNonNull(pokemonTypes));
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        template = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        url= pokemonServiceUrl;
    }
}