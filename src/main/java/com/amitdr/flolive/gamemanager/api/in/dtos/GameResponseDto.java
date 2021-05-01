package com.amitdr.flolive.gamemanager.api.in.dtos;

import com.amitdr.flolive.gamemanager.entities.Player;
import com.amitdr.flolive.gamemanager.entities.Question;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponseDto {

    @JsonProperty("gameId")
    private Integer gameId;

    @JsonProperty("leaderboard")
    private List<Player> leaderboard;

    @JsonProperty("questions")
    private List<Question> questions;
}
