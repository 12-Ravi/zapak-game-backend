// src/main/java/com/reliancegames/zapak/dto/LeaderboardEntry.java
package com.reliancegames.zapak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntry {
    private Integer position;
    private Long playerId;
    private String username;
    private Long score;
    private String country;
}