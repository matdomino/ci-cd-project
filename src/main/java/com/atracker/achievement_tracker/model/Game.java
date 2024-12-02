package com.atracker.achievement_tracker.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.UUID;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String category;
    private Date release_date;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Achievement> achievements;

    public Game() {}

    public Game(String title, String category, Date release_date) {
        this.title = title;
        this.category = category;
        this.release_date = release_date;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Date getRelease_date() { return release_date; }
    public void setRelease_date(Date release_date) { this.release_date = release_date; }

    public List<Achievement> getAchievements() { return achievements; }
    public void setAchievements(List<Achievement> achievements) { this.achievements = achievements; }


    @Override
    public String toString() {
        return "ID: " + id + ", tytuł " + title + ", kategoria: " + category + ", data wydania: " + release_date;
    }
}
