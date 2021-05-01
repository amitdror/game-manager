package com.amitdr.flolive.gamemanager.entities;

import lombok.Data;

@Data
public class Player {

    private String username;
    private Integer pointsEarned;

    public Player(String username) {
        this.username = username;
        this.pointsEarned = 0;
    }

    public void addPoints(Integer questionPoints) {
        this.pointsEarned += questionPoints;
    }
}
