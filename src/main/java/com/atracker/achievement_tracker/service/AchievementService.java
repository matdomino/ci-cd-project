package com.atracker.achievement_tracker.service;

import com.atracker.achievement_tracker.model.Achievement;
import com.atracker.achievement_tracker.model.Game;
import com.atracker.achievement_tracker.repository.AchievementRepository;
import com.atracker.achievement_tracker.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AchievementService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(UUID id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    public String deleteGameById(UUID id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return "Game deleted successfully.";
        } else {
            return "Game not found.";
        }
    }

    public Achievement addAchievementToGame(UUID gameId, Achievement achievement) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            achievement.setGame(game.get());
            return achievementRepository.save(achievement);
        }
        return null;
    }

    public Achievement updateAchievement(UUID gameId, UUID achievementId, Achievement achievement) {
        Optional<Achievement> existingAchievement = achievementRepository.findById(achievementId);

        if (existingAchievement.isPresent()) {
            Achievement updatedAchievement = existingAchievement.get();

            updatedAchievement.setType(achievement.getType());
            updatedAchievement.setDescription(achievement.getDescription());
            updatedAchievement.setAchievement_date(achievement.getAchievement_date());

            Optional<Game> game = gameRepository.findById(gameId);
            if (game.isPresent()) {
                updatedAchievement.setGame(game.get());
            } else {
                return null;
            }

            return achievementRepository.save(updatedAchievement);
        }

        return null;
    }


    public List<Achievement> getGamesAchievements(UUID gameId) {
        return achievementRepository.findByGameId(gameId);
    }
}
