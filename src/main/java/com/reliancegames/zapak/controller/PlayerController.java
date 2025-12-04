// src/main/java/com/reliancegames/zapak/controller/PlayerController.java
package com.reliancegames.zapak.controller;

import com.reliancegames.zapak.dto.ApiResponse;
import com.reliancegames.zapak.dto.PlayerProgressRequest;
import com.reliancegames.zapak.dto.PlayerRegistrationRequest;
import com.reliancegames.zapak.entity.Player;
import com.reliancegames.zapak.entity.PlayerProgress;
import com.reliancegames.zapak.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {
    
    private final PlayerService playerService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Player>> registerPlayer(
            @Valid @RequestBody PlayerRegistrationRequest request) {
        try {
            Player player = playerService.registerPlayer(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Player registered successfully", player));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping("/progress")
    public ResponseEntity<ApiResponse<PlayerProgress>> saveProgress(
            @Valid @RequestBody PlayerProgressRequest request) {
        try {
            PlayerProgress progress = playerService.savePlayerProgress(request);
            return ResponseEntity.ok(ApiResponse.success("Progress saved successfully", progress));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{playerId}/progress")
    public ResponseEntity<ApiResponse<PlayerProgress>> getProgress(@PathVariable Long playerId) {
        try {
            PlayerProgress progress = playerService.getPlayerProgress(playerId);
            return ResponseEntity.ok(ApiResponse.success(progress));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{playerId}")
    public ResponseEntity<ApiResponse<Player>> getPlayer(@PathVariable Long playerId) {
        try {
            Player player = playerService.getPlayerById(playerId);
            return ResponseEntity.ok(ApiResponse.success(player));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
}