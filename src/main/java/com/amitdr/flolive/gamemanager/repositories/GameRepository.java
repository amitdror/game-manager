package com.amitdr.flolive.gamemanager.repositories;

import com.amitdr.flolive.gamemanager.entities.Game;

public interface GameRepository {

    Game getGame(Integer gameId);

    Game saveGame(Game build);
}
