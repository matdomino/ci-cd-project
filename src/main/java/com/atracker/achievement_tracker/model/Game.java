package com.atracker.achievement_tracker.model;

import java.util.Date;

public class Game {
    private String title;
    private String category;
    private Date release_date;

    public Game(String title, String category, Date release_date) {
        this.title = title;
        this.category = category;
        this.release_date = release_date;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public Date getDate() { return release_date; }

    @Override
    public String toString() {
        return "Tytuł " + title + ", kategoria: " + category + ", data wydania: " + release_date;
    }
}
