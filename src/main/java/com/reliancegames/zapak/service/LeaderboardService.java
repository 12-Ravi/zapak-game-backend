// src/main/java/com/reliancegames/zapak/service/LeaderboardService.java
package com.reliancegames.zapak.service;

import com.reliancegames.zapak.dto.LeaderboardEntry;
import com.reliancegames.zapak.dto.ScoreSubmissionRequest;
import com.reliancegames.zapak.entity.Player;
import com.reliancegames.zapak.entity.PlayerScore;
import com.reliancegames.zapak.repository.PlayerRepository;
import com.reliancegames.zapak.repository.PlayerScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    
    private final PlayerScoreRepository playerScoreRepository;
    private final PlayerRepository playerRepository;
    
    @Transactional
    @CacheEvict(value = {"globalLeaderboard", "countryLeaderboard"}, allEntries = true)
    public PlayerScore submitScore(ScoreSubmissionRequest request) {
        Player player = playerRepository.findById(request.getPlayerId())
            .orElseThrow(() -> new RuntimeException("Player not found"));
        
        PlayerScore score = new PlayerScore();
        score.setPlayerId(request.getPlayerId());
        score.setGameId(request.getGameId());
        score.setScore(request.getScore());
        score.setTimestamp(LocalDateTime.now());
        score.setCountry(request.getCountry() != null ? request.getCountry() : player.getCountry());
        
        // Update player last active time
        player.setLastActiveTime(LocalDateTime.now());
        playerRepository.save(player);
        
        return playerScoreRepository.save(score);
    }
    
    @Cacheable(value = "globalLeaderboard", key = "#gameId + '_' + #limit")
    public List<LeaderboardEntry> getGlobalLeaderboard(String gameId, int limit) {
        List<PlayerScore> scores = playerScoreRepository.findTopByGameIdOrderByScoreDesc(
            gameId, PageRequest.of(0, limit)
        );
        
        return buildLeaderboardEntries(scores);
    }
    
    @Cacheable(value = "countryLeaderboard", key = "#gameId + '_' + #country + '_' + #limit")
    public List<LeaderboardEntry> getCountryLeaderboard(String gameId, String country, int limit) {
        List<PlayerScore> scores = playerScoreRepository.findTopByGameIdAndCountryOrderByScoreDesc(
            gameId, country, PageRequest.of(0, limit)
        );
        
        return buildLeaderboardEntries(scores);
    }
    
    private List<LeaderboardEntry> buildLeaderboardEntries(List<PlayerScore> scores) {
        List<LeaderboardEntry> entries = new ArrayList<>();
        int position = 1;
        
        for (PlayerScore score : scores) {
            Player player = playerRepository.findById(score.getPlayerId()).orElse(null);
            if (player != null) {
                entries.add(new LeaderboardEntry(
                    position++,
                    player.getId(),
                    player.getUsername(),
                    score.getScore(),
                    score.getCountry()
                ));
            }
        }
        
        return entries;
    }
}
