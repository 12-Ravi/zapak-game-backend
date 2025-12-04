// src/main/java/com/reliancegames/zapak/service/PlayerService.java
package com.reliancegames.zapak.service;

import com.reliancegames.zapak.dto.PlayerProgressRequest;
import com.reliancegames.zapak.dto.PlayerRegistrationRequest;
import com.reliancegames.zapak.entity.Player;
import com.reliancegames.zapak.entity.PlayerProgress;
import com.reliancegames.zapak.repository.PlayerProgressRepository;
import com.reliancegames.zapak.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlayerService {
    
    private final PlayerRepository playerRepository;
    private final PlayerProgressRepository playerProgressRepository;
    
    @Transactional
    public Player registerPlayer(PlayerRegistrationRequest request) {
        if (playerRepository.existsByDeviceId(request.getDeviceId())) {
            throw new RuntimeException("Player with this device ID already exists");
        }
        
        Player player = new Player();
        player.setDeviceId(request.getDeviceId());
        player.setUsername(request.getUsername());
        player.setPlatform(request.getPlatform());
        player.setCountry(request.getCountry());
        player.setCreationDate(LocalDateTime.now());
        player.setLastActiveTime(LocalDateTime.now());
        
        return playerRepository.save(player);
    }
    
    @Transactional
    @CacheEvict(value = "playerProgress", key = "#request.playerId")
    public PlayerProgress savePlayerProgress(PlayerProgressRequest request) {
        Player player = playerRepository.findById(request.getPlayerId())
            .orElseThrow(() -> new RuntimeException("Player not found"));
        
        PlayerProgress progress = playerProgressRepository.findByPlayerId(request.getPlayerId())
            .orElse(new PlayerProgress());
        
        progress.setPlayerId(request.getPlayerId());
        progress.setLevel(request.getLevel());
        progress.setRank(request.getRank());
        progress.setGold(request.getGold());
        progress.setCash(request.getCash());
        progress.setGems(request.getGems());
        progress.setRewardName(request.getRewardName());
        progress.setRewardId(request.getRewardId());
        progress.setCountry(request.getCountry() != null ? request.getCountry() : player.getCountry());
        progress.setLastActiveTime(LocalDateTime.now());
        progress.setUpdatedAt(LocalDateTime.now());
        
        // Update player last active time
        player.setLastActiveTime(LocalDateTime.now());
        playerRepository.save(player);
        
        return playerProgressRepository.save(progress);
    }
    
    @Cacheable(value = "playerProgress", key = "#playerId")
    public PlayerProgress getPlayerProgress(Long playerId) {
        return playerProgressRepository.findByPlayerId(playerId)
            .orElseThrow(() -> new RuntimeException("Player progress not found"));
    }
    
    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
            .orElseThrow(() -> new RuntimeException("Player not found"));
    }
}