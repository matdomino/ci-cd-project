package com.atracker.achievement_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Type cannot be null")
    @Size(min = 1, message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Description cannot be null")
    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Achievement date cannot be null")
    private LocalDateTime achievementDate;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Achievement() {}

    public Achievement(String type, String description, LocalDateTime achievementDate) {
        this.type = type;
        this.description = description;
        this.achievementDate = achievementDate;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getAchievementDate() { return achievementDate; }
    public void setAchievementDate(LocalDateTime achievementDate) { this.achievementDate = achievementDate; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }


    @Override
    public String toString() {
        return "Osiągnięcie: " + id + ", typ: " + type + ", opis: " + description + ", data: " + achievementDate;
    }
}
