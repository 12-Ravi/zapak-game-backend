// src/main/java/com/reliancegames/zapak/dto/PlayerProgressRequest.java
package com.reliancegames.zapak.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerProgressRequest {
    @NotNull(message = "Player ID is required")
    private Long playerId;
    
    @NotNull(message = "Level is required")
    @Min(value = 1, message = "Level must be at least 1")
    private Integer level;
    
    @NotNull(message = "Rank is required")
    @Min(value = 1, message = "Rank must be at least 1")
    private Integer rank;
    
    @NotNull(message = "Gold is required")
    @Min(value = 0, message = "Gold cannot be negative")
    private Long gold;
    
    @NotNull(message = "Cash is required")
    @Min(value = 0, message = "Cash cannot be negative")
    private Long cash;
    
    @NotNull(message = "Gems is required")
    @Min(value = 0, message = "Gems cannot be negative")
    private Long gems;
    
    private String rewardName;
    private String rewardId;
    private String country;
}
