package com.atracker.achievement_tracker.controller;

import com.atracker.achievement_tracker.model.Achievement;
import com.atracker.achievement_tracker.model.Game;
import com.atracker.achievement_tracker.repository.GameRepository;
import com.atracker.achievement_tracker.repository.AchievementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ControllerIntegrationTests {
    @Container
    public static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:17.2");


    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    private Game testGame;

    @BeforeEach
    void setUp() {
        achievementRepository.deleteAll();
        gameRepository.deleteAll();

        testGame = new Game("Game 1", "RPG", LocalDateTime.of(2025, 1, 1, 12, 0));
        gameRepository.save(testGame);
    }

    @Test
    void testAddGame() throws Exception {
        String gameJson = "{"
                + "\"title\": \"Game 2\", "
                + "\"category\": \"FPS\", "
                + "\"releaseDate\": \"" + LocalDateTime.of(2025, 1, 1, 12, 0) + "\""
                + "}";

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Game 2"));
    }

    @Test
    void testGetAllGames() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Game 1"));
    }

    @Test
    void testGetGameById() throws Exception {
        mockMvc.perform(get("/games/" + testGame.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Game 1"));
    }

    @Test
    void testAddAchievement() throws Exception {
        String achievementJson = "{"
                + "\"type\": \"Rare\", "
                + "\"description\": \"Nice description\", "
                + "\"achievementDate\": \"" + LocalDateTime.of(2024, 1, 1, 12, 0) + "\""
                + "}";

        mockMvc.perform(post("/games/" + testGame.getId() + "/achievements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(achievementJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Rare"));
    }

    @Test
    void testGetAchievementsForGame() throws Exception {
        Achievement achievement = new Achievement("Rare", "Nice description", LocalDateTime.of(2024, 1, 1, 12, 0));
        achievement.setGame(testGame);
        achievementRepository.save(achievement);

        mockMvc.perform(get("/games/" + testGame.getId() + "/achievements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type").value("Rare"));
    }

    @Test
    void testDeleteGame() throws Exception {
        mockMvc.perform(delete("/games/" + testGame.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/games/" + testGame.getId()))
                .andExpect(status().isOk());
    }
}