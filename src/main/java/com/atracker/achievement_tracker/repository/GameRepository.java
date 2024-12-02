package com.atracker.achievement_tracker.repository;

import com.atracker.achievement_tracker.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
