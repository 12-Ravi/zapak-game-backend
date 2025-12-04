// src/main/java/com/reliancegames/zapak/entity/Player.java
package com.reliancegames.zapak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "players", indexes = {
    @Index(name = "idx_device_id", columnList = "deviceId"),
    @Index(name = "idx_username", columnList = "username")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String deviceId;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String platform; // iOS, Android, Web
    
    @Column(nullable = false)
    private LocalDateTime creationDate;
    
    @Column
    private LocalDateTime lastActiveTime;
    
    @Column
    private String country;
}