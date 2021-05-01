package com.amitdr.flolive.gamemanager.api.in.controllers;

import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerResponseDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameResponseDto;
import com.amitdr.flolive.gamemanager.entities.Player;
import com.amitdr.flolive.gamemanager.service.GameManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trivia")
public class GameController {

    private final GameManager gameManager;

    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/{gameId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Player> getLeaderBoard(@PathVariable Integer gameId) {
        return gameManager.getLeaderboard(gameId);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public AnswerResponseDto answerQuestion(@RequestBody AnswerRequestDto userAnswer) {
        return gameManager.answerQuestion(userAnswer);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public GameResponseDto createGame(@RequestBody GameRequestDto request) {
        return gameManager.createNewGame(request);
    }
}
