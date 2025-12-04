// src/main/java/com/reliancegames/zapak/repository/PlayerRepository.java
package com.reliancegames.zapak.repository;

import com.reliancegames.zapak.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByDeviceId(String deviceId);
    boolean existsByDeviceId(String deviceId);
}