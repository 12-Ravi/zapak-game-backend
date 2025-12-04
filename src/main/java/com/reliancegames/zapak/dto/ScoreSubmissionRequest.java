// src/main/java/com/reliancegames/zapak/dto/ScoreSubmissionRequest.java
package com.reliancegames.zapak.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreSubmissionRequest {
    @NotNull(message = "Player ID is required")
    private Long playerId;
    
    @NotBlank(message = "Game ID is required")
    private String gameId;
    
    @NotNull(message = "Score is required")
    private Long score;
    
    private String country;
}