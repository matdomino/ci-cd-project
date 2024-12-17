package com.atracker.achievement_tracker.controller;

import com.atracker.achievement_tracker.model.Achievement;
import com.atracker.achievement_tracker.model.Game;
import com.atracker.achievement_tracker.service.AchievementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RESTContoller.class)
public class RESTControllerTest {

    @MockBean
    private AchievementService achievementService;

    @Autowired
    private MockMvc mockMvc;

    private UUID exampleGameId;
    private UUID exampleAchievementId;
    private Game exampleGame;
    private Achievement exampleAchievement;

    @BeforeEach
    public void setup() {
        exampleGameId = UUID.randomUUID();
        exampleAchievementId = UUID.randomUUID();

        exampleGame = new Game("Example Game", "Action", LocalDateTime.of(2024, 1, 1, 12, 0));
        exampleGame.setId(exampleGameId);

        exampleAchievement = new Achievement("Example Type", "Example Description", LocalDateTime.of(2024, 1, 1, 12, 0));
        exampleAchievement.setId(exampleAchievementId);
        exampleAchievement.setGame(exampleGame);
    }

    @Test
    public void testAddGame() throws Exception {
        when(achievementService.addGame(any(Game.class))).thenReturn(exampleGame);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Example Game\", \"category\": \"Action\", \"releaseDate\": \"2024-01-01T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Example Game"))
                .andExpect(jsonPath("$.category").value("Action"));
    }

    @Test
    public void testGetAllGames() throws Exception {
        List<Game> games = List.of(exampleGame);
        when(achievementService.getAllGames()).thenReturn(games);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Example Game"))
                .andExpect(jsonPath("$[0].category").value("Action"));
    }

    @Test
    public void testGetGameById() throws Exception {
        when(achievementService.getGameById(exampleGameId)).thenReturn(exampleGame);

        mockMvc.perform(get("/games/{id}", exampleGameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Example Game"))
                .andExpect(jsonPath("$.category").value("Action"));
    }

    @Test
    public void testDeleteGameById() throws Exception {
        when(achievementService.deleteGameById(exampleGameId)).thenReturn("Game deleted successfully.");

        mockMvc.perform(delete("/games/{id}", exampleGameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Game deleted successfully."));
    }

    @Test
    public void testAddAchievement() throws Exception {
        when(achievementService.addAchievementToGame(any(UUID.class), any(Achievement.class))).thenReturn(exampleAchievement);

        mockMvc.perform(post("/games/{id}/achievements", exampleGameId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\": \"Example Type\", \"description\": \"Example Description\", \"achievementDate\": \"2024-01-01T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Example Type"))
                .andExpect(jsonPath("$.description").value("Example Description"));
    }

    @Test
    public void testUpdateAchievement() throws Exception {
        when(achievementService.updateAchievement(any(UUID.class), any(UUID.class), any(Achievement.class)))
                .thenReturn(exampleAchievement);

        mockMvc.perform(put("/games/{gameId}/achievements/{achievementId}", exampleGameId, exampleAchievementId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\": \"Updated Type\", \"description\": \"Updated Description\", \"achievementDate\": \"2024-01-01T15:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Example Type"))
                .andExpect(jsonPath("$.description").value("Example Description"));
    }

    @Test
    public void testGetAchievements() throws Exception {
        List<Achievement> achievements = List.of(exampleAchievement);
        when(achievementService.getGamesAchievements(exampleGameId)).thenReturn(achievements);

        mockMvc.perform(get("/games/{id}/achievements", exampleGameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].type").value("Example Type"))
                .andExpect(jsonPath("$[0].description").value("Example Description"));
    }
}
