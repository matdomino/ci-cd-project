package com.atracker.achievement_tracker.controller;

import com.atracker.achievement_tracker.model.Achievement;
import com.atracker.achievement_tracker.model.Game;
import com.atracker.achievement_tracker.service.AchievementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ControllerUnitTests {

    @Mock
    private AchievementService achievementService;

    @InjectMocks
    private RESTContoller restController;

    private Game exampleGame;
    private Achievement exampleAchievement;
    private UUID exampleGameId;
    private UUID exampleAchievementId;

    @BeforeEach
    void setUp() {
        exampleGameId = UUID.randomUUID();
        exampleAchievementId = UUID.randomUUID();

        exampleGame = new Game("Game 1", "FPS", LocalDateTime.of(2025, 1, 1, 12, 0));
        exampleGame.setId(exampleGameId);

        exampleAchievement = new Achievement("Rare", "Nice description", LocalDateTime.of(2024, 1, 1, 12, 0));
        exampleAchievement.setId(exampleAchievementId);
        exampleAchievement.setGame(exampleGame);
    }

    @Test
    void testAddGame() {
        when(achievementService.addGame(any(Game.class))).thenReturn(exampleGame);

        Game result = restController.addGame(exampleGame);

        assertEquals(exampleGame, result);
        verify(achievementService).addGame(exampleGame);
    }

    @Test
    void testGetAllGames() {
        List<Game> games = List.of(exampleGame);
        when(achievementService.getAllGames()).thenReturn(games);

        List<Game> result = restController.getAllGames();

        assertEquals(games, result);
        verify(achievementService).getAllGames();
    }

    @Test
    void testGetGameById() {
        when(achievementService.getGameById(exampleGameId)).thenReturn(exampleGame);

        Game result = restController.getGameById(exampleGameId);

        assertEquals(exampleGame, result);
        verify(achievementService).getGameById(exampleGameId);
    }

    @Test
    void testDeleteGameById() {
        when(achievementService.deleteGameById(exampleGameId)).thenReturn("Game deleted successfully.");

        String response = restController.deleteGameById(exampleGameId);

        assertEquals("Game deleted successfully.", response);
        verify(achievementService).deleteGameById(exampleGameId);
    }

    @Test
    void testAddAchievement() {
        when(achievementService.addAchievementToGame(any(UUID.class), any(Achievement.class)))
                .thenReturn(exampleAchievement);

        Achievement result = restController.addAchievement(exampleGameId, exampleAchievement);

        assertEquals(exampleAchievement, result);
        verify(achievementService).addAchievementToGame(exampleGameId, exampleAchievement);
    }

    @Test
    void testUpdateAchievement() {
        when(achievementService.updateAchievement(any(UUID.class), any(UUID.class), any(Achievement.class)))
                .thenReturn(exampleAchievement);

        Achievement result = restController.updateAchievement(exampleGameId, exampleAchievementId, exampleAchievement);

        assertEquals(exampleAchievement, result);
        verify(achievementService).updateAchievement(exampleGameId, exampleAchievementId, exampleAchievement);
    }

    @Test
    void testGetAllAchievements() {
        List<Achievement> achievements = List.of(exampleAchievement);
        when(achievementService.getGamesAchievements(exampleGameId)).thenReturn(achievements);

        List<Achievement> result = restController.getAllAchievements(exampleGameId);

        assertEquals(achievements, result);
        verify(achievementService).getGamesAchievements(exampleGameId);
    }
}
