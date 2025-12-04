// src/main/java/com/reliancegames/zapak/repository/PlayerProgressRepository.java
package com.reliancegames.zapak.repository;

import com.reliancegames.zapak.entity.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {
    Optional<PlayerProgress> findByPlayerId(Long playerId);
}