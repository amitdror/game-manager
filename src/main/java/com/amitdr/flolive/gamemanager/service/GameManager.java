package com.amitdr.flolive.gamemanager.service;

import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerResponseDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameResponseDto;
import com.amitdr.flolive.gamemanager.entities.Game;
import com.amitdr.flolive.gamemanager.entities.Player;
import com.amitdr.flolive.gamemanager.entities.Question;
import com.amitdr.flolive.gamemanager.enums.AnswerStatus;
import com.amitdr.flolive.gamemanager.factories.GameFactory;
import com.amitdr.flolive.gamemanager.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameManager {

    private final GameRepository repository;
    private final GameFactory gameFactory;

    @Autowired
    public GameManager(GameRepository repository, GameFactory gameFactory) {
        this.repository = repository;
        this.gameFactory = gameFactory;
    }

    public List<Player> getLeaderboard(Integer gameId) {
        return repository.getGame(gameId).getLeaderboard();
    }

    public AnswerResponseDto answerQuestion(AnswerRequestDto userAnswer) {
        Game game = repository.getGame(userAnswer.getGameId());
        Question question = game.getQuestionById(userAnswer.getQuestionId());
        Integer answerPoints = question.checkAnswerPoints(userAnswer.getAnswerId());
        AnswerStatus answerStatus = question.checkAnswerStatus(userAnswer.getAnswerId());
        Player player = game.getPlayerByUsername(userAnswer.getUsername());
        player.addPoints(answerPoints);
        return AnswerResponseDto.builder()
                .answerStatus(answerStatus)
                .pointsEarned(answerPoints)
                .build();
    }

    public GameResponseDto createNewGame(GameRequestDto request) {
        Game newGame = gameFactory.buildNewGame(request);
        Game savedGame = repository.saveGame(newGame);
        return GameResponseDto.builder()
                .gameId(savedGame.getGameId())
                .questions(new ArrayList<>(savedGame.getQuestions().values()))
                .leaderboard(savedGame.getLeaderboard())
                .build();
    }
}
