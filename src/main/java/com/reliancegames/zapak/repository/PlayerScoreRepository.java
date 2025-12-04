// src/main/java/com/reliancegames/zapak/repository/PlayerScoreRepository.java
package com.reliancegames.zapak.repository;

import com.reliancegames.zapak.entity.PlayerScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Long> {
    
    @Query("SELECT ps FROM PlayerScore ps WHERE ps.gameId = :gameId ORDER BY ps.score DESC")
    List<PlayerScore> findTopByGameIdOrderByScoreDesc(@Param("gameId") String gameId, Pageable pageable);
    
    @Query("SELECT ps FROM PlayerScore ps WHERE ps.gameId = :gameId AND ps.country = :country ORDER BY ps.score DESC")
    List<PlayerScore> findTopByGameIdAndCountryOrderByScoreDesc(
        @Param("gameId") String gameId, 
        @Param("country") String country, 
        Pageable pageable
    );
}
