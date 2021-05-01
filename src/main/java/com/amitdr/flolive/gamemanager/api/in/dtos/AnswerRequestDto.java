package com.amitdr.flolive.gamemanager.api.in.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerRequestDto {

    @JsonProperty("username")
    @NotNull(message = "username is missing..")
    private String username;

    @JsonProperty("gameId")
    @NotNull(message = "game id is missing..")
    private Integer gameId;

    @JsonProperty("answerId")
    @NotNull(message = "answer id is missing..")
    private String answerId;

    @JsonProperty("questionId")
    @NotNull(message = "question id is missing..")
    private String questionId;
}
