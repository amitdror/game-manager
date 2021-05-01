package com.amitdr.flolive.gamemanager.api.out.clients;

import com.amitdr.flolive.gamemanager.api.out.dtos.TriviaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "triviaClient", url = "https://opentdb.com/api.php")
public interface TriviaClient {

    @GetMapping(value = "?amount=10&type=multiple", produces = "application/json")
    TriviaDto getRandomQuestions();
}
