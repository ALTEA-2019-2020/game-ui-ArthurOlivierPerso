package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {
    TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trainers");
        modelAndView.addObject("trainers", trainerService.listTrainers());
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

    @Autowired
    public void setTrainerService(TrainerService trainerService){
        this.trainerService = trainerService;
    }
}
