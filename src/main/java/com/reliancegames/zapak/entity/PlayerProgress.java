// src/main/java/com/reliancegames/zapak/entity/PlayerProgress.java
package com.reliancegames.zapak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_progress", indexes = {
    @Index(name = "idx_player_id", columnList = "playerId")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long playerId;
    
    @Column(nullable = false)
    private Integer level;
    
    @Column(name = "player_rank", nullable = false)
    private Integer rank;
    
    @Column(nullable = false)
    private Long gold;
    
    @Column(nullable = false)
    private Long cash;
    
    @Column(nullable = false)
    private Long gems;
    
    @Column
    private String rewardName;
    
    @Column
    private String rewardId;
    
    @Column
    private LocalDateTime lastActiveTime;
    
    @Column
    private String country;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}