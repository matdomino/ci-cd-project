package com.atracker.achievement_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Category cannot be null")
    @Size(min = 1, message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Release date cannot be null")
    private LocalDateTime releaseDate;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Achievement> achievements;

    public Game() {}

    public Game(String title, String category, LocalDateTime releaseDate) {
        this.title = title;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getRelease_date() { return releaseDate; }
    public void setRelease_date(LocalDateTime releaseDate) { this.releaseDate = releaseDate; }

    public List<Achievement> getAchievements() { return achievements; }
    public void setAchievements(List<Achievement> achievements) { this.achievements = achievements; }


    @Override
    public String toString() {
        return "ID: " + id + ", tytuł " + title + ", kategoria: " + category + ", data wydania: " + releaseDate;
    }
}
