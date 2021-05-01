package com.amitdr.flolive.gamemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GameManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameManagerApplication.class, args);
    }
}
