package com.amitdr.flolive.gamemanager.factories;

import com.amitdr.flolive.gamemanager.api.in.dtos.GameRequestDto;
import com.amitdr.flolive.gamemanager.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {

    private final QuestionFactory questionFactory;

    @Autowired
    public GameFactory(QuestionFactory questionFactory) {
        this.questionFactory = questionFactory;
    }

    public Game buildNewGame(GameRequestDto gameRequest) {
        Game newGame = new Game();
        newGame.addQuestions(questionFactory.getRandomQuestions());
        newGame.addPlayers(gameRequest.getPlayers());
        return newGame;
    }
}
