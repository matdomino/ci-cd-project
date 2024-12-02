package com.atracker.achievement_tracker.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String type;
    private String description;
    private Date achievement_date;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Achievement() {}

    public Achievement(String type, String description, Date achievement_date) {
        this.type = type;
        this.description = description;
        this.achievement_date = achievement_date;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getAchievement_date() { return achievement_date; }
    public void setAchievement_date(Date achievement_date) { this.achievement_date = achievement_date; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }


    @Override
    public String toString() {
        return "Osiągnięcie: " + id + ", typ: " + type + ", opis: " + description + ", data: " + achievement_date;
    }
}
