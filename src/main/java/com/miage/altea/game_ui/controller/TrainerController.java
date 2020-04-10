package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class TrainerController {
    TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trainers");
        modelAndView.addObject("trainers", trainerService.listTrainersWithoutOne(principal.getName()));
        return modelAndView;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainer(@PathVariable String name){
        Trainer trainer = trainerService.trainerByName(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trainer");
        modelAndView.addObject("trainer", trainerService.trainerByName(name));
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile(Principal principal){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("trainer");
        var trainer = trainerService.trainerByName(principal.getName());
        modelAndView.addObject("trainer", trainer);
        return modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService){
        this.trainerService = trainerService;
    }
}
