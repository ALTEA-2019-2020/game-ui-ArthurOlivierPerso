package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    String username;

    @Value("${trainer.service.password}")
    String password;

    @Bean
    RestTemplate trainerApiRestTemplate(){
        var restTemplate = new RestTemplate();
        var basicAuthInterceptor = new BasicAuthenticationInterceptor(username, password);
        restTemplate.getInterceptors().add(basicAuthInterceptor);
        return restTemplate;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}