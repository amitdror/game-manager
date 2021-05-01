package com.amitdr.flolive.gamemanager.entities;

import com.amitdr.flolive.gamemanager.exceptions.PlayerNotFoundException;
import com.amitdr.flolive.gamemanager.exceptions.QuestionNotFoundException;
import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Game {

    private Integer gameId;
    private Map<String, Player> players;
    private Map<String, Question> questions;

    public Game() {
        this.gameId = null;
        this.players = new HashMap<>();
        this.questions = new HashMap<>();
    }

    public List<Player> getLeaderboard() {
        return players.values()
                .stream()
                .sorted(byHighestPointEarned())
                .collect(Collectors.toList());
    }

    public Player getPlayerByUsername(String username) {
        if (!players.containsKey(username)) throw new PlayerNotFoundException();
        return players.get(username);
    }

    public Question getQuestionById(String questionId) {
        if (!questions.containsKey(questionId)) throw new QuestionNotFoundException();
        return questions.get(questionId);
    }

    public void addPlayers(List<String> newPlayers) {
        if (newPlayers == null) return;
        for (String newPlayerUsername : newPlayers) {
            if (newPlayerUsername != null && !players.containsKey(newPlayerUsername)) {
                players.put(newPlayerUsername, new Player(newPlayerUsername));
            }
        }
    }

    public void addQuestions(List<Question> newQuestions) {
        if (newQuestions == null) return;
        for (Question newQuestion : newQuestions) {
            if (newQuestion != null && !questions.containsKey(newQuestion.getQuestionId())) {
                questions.put(newQuestion.getQuestionId(), newQuestion);
            }
        }
    }

    private Comparator<Player> byHighestPointEarned() {
        return Comparator.comparing(Player::getPointsEarned);
    }
}
