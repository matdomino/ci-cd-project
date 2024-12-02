package com.atracker.achievement_tracker.controller;

import com.atracker.achievement_tracker.model.Achievement;
import com.atracker.achievement_tracker.model.Game;
import com.atracker.achievement_tracker.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/games")
public class RESTContoller {
    @Autowired
    private AchievementService achievementService;

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return achievementService.addGame(game);
    }

    @GetMapping
    public List<Game> getAllGames() {
        return achievementService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable("id") UUID id) {
        return achievementService.getGameById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteGameById(@PathVariable("id") UUID id) {
        return achievementService.deleteGameById(id);
    }

    @PostMapping("/{id}/achievements")
    public Achievement addAchievement(@PathVariable("id") UUID gameId, @RequestBody Achievement achievement) {
        return achievementService.addAchievementToGame(gameId, achievement);
    }

    @PutMapping("/{gameId}/achievements/{achievementId}")
    public Achievement updateAchievement(@PathVariable("gameId") UUID gameId, @PathVariable("achievementId") UUID achievementId, @RequestBody Achievement achievement) {
        return achievementService.updateAchievement(gameId, achievementId, achievement);
    }

    @GetMapping("/{id}/achievements")
    public List<Achievement> getAllAchievements(@PathVariable("id") UUID gameId) {
        return achievementService.getGamesAchievements(gameId);
    }
}
