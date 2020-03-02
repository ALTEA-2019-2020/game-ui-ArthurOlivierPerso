package com.miage.altea.game_ui.trainers.bo;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

import java.util.List;

public class Trainer {
    private String name;

    private List<PokemonType> pokemonTypes;

    private List<Team> team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getPokemonTypes() {
        return pokemonTypes;
    }

    public void setPokemonTypes(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }
}
