package com.amitdr.flolive.gamemanager.repositories;

import com.amitdr.flolive.gamemanager.entities.Game;
import com.amitdr.flolive.gamemanager.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InMemoryRepository implements GameRepository {

    private final Map<Integer, Game> database;

    @Autowired
    public InMemoryRepository(@Qualifier("in-memory-datasource") Map<Integer, Game> database) {
        this.database = database;
    }

    @Override
    public Game getGame(Integer gameId) {
        Game game = database.getOrDefault(gameId, null);
        if (game == null) throw new GameNotFoundException();
        return game;
    }

    @Override
    public Game saveGame(Game newGame) {
        if (!database.containsKey(newGame.getGameId())) newGame.setGameId(database.size() + 1);
        database.put(newGame.getGameId(), newGame);
        return newGame;
    }
}
