package com.atracker.achievement_tracker.repository;

import com.atracker.achievement_tracker.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {
    List<Achievement> findByGameId(UUID gameId);
}
