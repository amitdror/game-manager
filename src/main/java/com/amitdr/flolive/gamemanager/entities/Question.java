package com.amitdr.flolive.gamemanager.entities;

import com.amitdr.flolive.gamemanager.enums.AnswerStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {

    private final static int NO_POINTS_EARNED = 0;
    private String questionId;
    private String correctAnswerId;
    private List<String> incorrectAnswers;
    private Integer questionPoints;

    public Integer checkAnswerPoints(String userAnswerId) {
        if (isAnswerCorrect(userAnswerId)) return questionPoints;
        else return NO_POINTS_EARNED;
    }

    public AnswerStatus checkAnswerStatus(String userAnswerId) {
        if (isAnswerCorrect(userAnswerId)) return AnswerStatus.Correct;
        else return AnswerStatus.Incorrect;
    }

    private boolean isAnswerCorrect(String userAnswerId) {
        return correctAnswerId.equalsIgnoreCase(userAnswerId);
    }
}
