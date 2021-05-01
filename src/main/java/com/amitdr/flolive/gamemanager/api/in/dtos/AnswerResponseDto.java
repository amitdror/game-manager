package com.amitdr.flolive.gamemanager.api.in.dtos;

import com.amitdr.flolive.gamemanager.enums.AnswerStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {

    @JsonProperty("answerStatus")
    private AnswerStatus answerStatus;

    @JsonProperty("pointsEarned")
    private Integer pointsEarned;
}
