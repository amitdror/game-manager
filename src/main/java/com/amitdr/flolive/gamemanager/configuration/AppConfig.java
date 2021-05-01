package com.amitdr.flolive.gamemanager.configuration;

import com.amitdr.flolive.gamemanager.entities.Game;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("in-memory-datasource")
    public Map<Integer, Game> inMemoryDatasource() {
        return new HashMap<>();
    }
}
