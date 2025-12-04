// src/main/java/com/reliancegames/zapak/entity/PlayerScore.java
package com.reliancegames.zapak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_scores", indexes = {
    @Index(name = "idx_player_game", columnList = "playerId,gameId"),
    @Index(name = "idx_game_score", columnList = "gameId,score"),
    @Index(name = "idx_game_country_score", columnList = "gameId,country,score")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long playerId;
    
    @Column(nullable = false)
    private String gameId;
    
    @Column(nullable = false)
    private Long score;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column
    private String country;
}