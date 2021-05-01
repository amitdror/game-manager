package com.amitdr.flolive.gamemanager;

import com.amitdr.flolive.gamemanager.api.in.controllers.GameController;
import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.AnswerResponseDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameRequestDto;
import com.amitdr.flolive.gamemanager.api.in.dtos.GameResponseDto;
import com.amitdr.flolive.gamemanager.entities.Player;
import com.amitdr.flolive.gamemanager.entities.Question;
import com.amitdr.flolive.gamemanager.enums.AnswerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class GameManagerApplicationTests {

    @Autowired
    private GameController gameController;

    @Test
    void gameController_AnswerQuestion_FirstPlayersAnswerCorrect() {
        //Arrange
        GameResponseDto newGame = gameController.createGame(gameRequest());
        Player firstPlayer = newGame.getLeaderboard().get(0);
        Question firstQuestion = newGame.getQuestions().get(0);
        AnswerRequestDto userAnswer = new AnswerRequestDto(firstPlayer.getUsername(), newGame.getGameId(), firstQuestion.getCorrectAnswerId(), firstQuestion.getQuestionId());
        //Act
        AnswerResponseDto actualAnswerResponse = gameController.answerQuestion(userAnswer);
        //Assert
        AnswerResponseDto expectedAnswerResponse = new AnswerResponseDto(AnswerStatus.Correct, firstQuestion.getQuestionPoints());
        assertEquals(actualAnswerResponse, expectedAnswerResponse);
    }

    @Test
    void gameController_AnswerQuestion_FirstPlayersAnswerIncorrect() {
        //Arrange
        GameResponseDto newGame = gameController.createGame(gameRequest());
        Player firstPlayer = newGame.getLeaderboard().get(0);
        Question firstQuestion = newGame.getQuestions().get(0);
        AnswerRequestDto userAnswer = new AnswerRequestDto(firstPlayer.getUsername(), newGame.getGameId(), firstQuestion.getIncorrectAnswers().get(0), firstQuestion.getQuestionId());
        //Act
        AnswerResponseDto actualAnswerResponse = gameController.answerQuestion(userAnswer);
        //Assert
        AnswerResponseDto expectedAnswerResponse = new AnswerResponseDto(AnswerStatus.Incorrect, 0);
        assertEquals(actualAnswerResponse, expectedAnswerResponse);
    }

    @Test
    void gameController_getLeaderboard_LastPlayerAnswerCorrect() {
        //Arrange
        GameResponseDto newGame = gameController.createGame(gameRequest());
        List<Player> expectedLeaderboard = newGame.getLeaderboard();
        Player lastPlayer = expectedLeaderboard.get(expectedLeaderboard.size() - 1);
        Question firstQuestion = newGame.getQuestions().get(0);
        AnswerRequestDto userAnswer = new AnswerRequestDto(lastPlayer.getUsername(), newGame.getGameId(), firstQuestion.getCorrectAnswerId(), firstQuestion.getQuestionId());
        //Act
        gameController.answerQuestion(userAnswer);
        List<Player> actualLeaderBoard = gameController.getLeaderBoard(newGame.getGameId());
        //Assert
        expectedLeaderboard.remove(lastPlayer);
        expectedLeaderboard.add(lastPlayer);
        assertEquals(actualLeaderBoard, expectedLeaderboard);
    }

    private GameRequestDto gameRequest() {
        return GameRequestDto.builder()
                .players(Arrays.asList("Tal", "Sigal", "Gaston"))
                .build();
    }
}
