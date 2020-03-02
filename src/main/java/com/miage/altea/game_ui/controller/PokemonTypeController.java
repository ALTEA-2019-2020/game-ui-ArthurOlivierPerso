package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {
    PokemonTypeService pokemonService;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pokedex");
        modelAndView.addObject("pokemonTypes", pokemonService.listPokemonsTypes());
        return modelAndView;
    }


    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService){
        pokemonService = pokemonTypeService;
    }
}
