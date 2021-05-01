package com.amitdr.flolive.gamemanager.factories;

import com.amitdr.flolive.gamemanager.api.out.clients.TriviaClient;
import com.amitdr.flolive.gamemanager.api.out.dtos.TriviaDto;
import com.amitdr.flolive.gamemanager.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuestionFactory {

    private final TriviaClient triviaClient;

    @Autowired
    public QuestionFactory(TriviaClient triviaClient) {
        this.triviaClient = triviaClient;
    }

    public List<Question> getRandomQuestions() {
        return triviaClient.getRandomQuestions().getResults()
                .stream()
                .map(toQuestionEntity())
                .collect(Collectors.toList());
    }

    private Function<TriviaDto.RandomQuestion, Question> toQuestionEntity() {
        return dto -> Question.builder()
                .questionId(dto.getQuestionDescription())
                .correctAnswerId(dto.getCorrectAnswer())
                .incorrectAnswers(dto.getIncorrectAnswers())
                .questionPoints(calculateQuestionPoints(dto))
                .build();
    }

    private Integer calculateQuestionPoints(TriviaDto.RandomQuestion dto) {
        switch (dto.getDifficulty()) {
            case "hard":
                return 10;
            case "medium":
                return 5;
            case "easy":
                return 1;
            default:
                return 0;
        }
    }
}
