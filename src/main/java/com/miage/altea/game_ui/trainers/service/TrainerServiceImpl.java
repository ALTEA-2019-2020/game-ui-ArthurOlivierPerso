package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Team;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private String url;
    private RestTemplate template;
    @Autowired
    private PokemonTypeService pokemonTypeService;

    @Override
    public List<Trainer> listTrainers() {
        var trainers = template.getForObject(url+"/trainers/", Trainer[].class);
        return List.of(Objects.requireNonNull(trainers));
    }

    @Override
    public List<Trainer> listTrainersWithoutOne(String name) {
        var trainers = template.getForObject(url+"/trainers/", Trainer[].class);
        List<Trainer> trainerList = List.of(Objects.requireNonNull(trainers));
        return trainerList.stream().filter(trainer -> !trainer.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public Trainer trainerByName(String name){
        Trainer trainer = template.getForObject(url+"/trainers/"+name, Trainer.class);
        List<PokemonType> pokemonTypes = pokemonTypeService.listPokemonsTypes();

        for(Team team: Objects.requireNonNull(trainer).getTeam()){
            List<PokemonType> list = (trainer.getPokemonTypes() == null) ? new ArrayList<>() : trainer.getPokemonTypes();
            PokemonType pokemonType = pokemonTypes.stream().filter(pType ->pType.getId()==team.getPokemonTypeId()).findFirst().get();
            pokemonType.setLevel(team.getLevel());
            list.add(pokemonType);
            trainer.setPokemonTypes(list);
        }
        return trainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        template = restTemplate;
    }


    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl) {
        url= trainerServiceUrl;
    }
}
