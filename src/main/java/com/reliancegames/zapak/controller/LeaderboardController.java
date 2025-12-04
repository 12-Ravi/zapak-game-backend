// src/main/java/com/reliancegames/zapak/controller/LeaderboardController.java
package com.reliancegames.zapak.controller;

import com.reliancegames.zapak.dto.ApiResponse;
import com.reliancegames.zapak.dto.LeaderboardEntry;
import com.reliancegames.zapak.dto.ScoreSubmissionRequest;
import com.reliancegames.zapak.entity.PlayerScore;
import com.reliancegames.zapak.service.LeaderboardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
    
    private final LeaderboardService leaderboardService;
    
    @PostMapping("/scores")
    public ResponseEntity<ApiResponse<PlayerScore>> submitScore(
            @Valid @RequestBody ScoreSubmissionRequest request) {
        try {
            PlayerScore score = leaderboardService.submitScore(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Score submitted successfully", score));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/global/{gameId}")
    public ResponseEntity<ApiResponse<List<LeaderboardEntry>>> getGlobalLeaderboard(
            @PathVariable String gameId,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<LeaderboardEntry> leaderboard = leaderboardService.getGlobalLeaderboard(gameId, limit);
            return ResponseEntity.ok(ApiResponse.success(leaderboard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/country/{gameId}/{country}")
    public ResponseEntity<ApiResponse<List<LeaderboardEntry>>> getCountryLeaderboard(
            @PathVariable String gameId,
            @PathVariable String country,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            List<LeaderboardEntry> leaderboard = leaderboardService.getCountryLeaderboard(gameId, country, limit);
            return ResponseEntity.ok(ApiResponse.success(leaderboard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
}
