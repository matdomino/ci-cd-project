package com.atracker.achievement_tracker.model;

import java.util.Date;
import java.util.UUID;

public class Achievement {
    private UUID id;
    private String type;
    private String description;
    private Date achievement_date;

    public Achievement(UUID id, String type, String description, Date achievement_date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.achievement_date = achievement_date;
    }

    @Override
    public String toString() {
        return "Osiągnięcie: " + id + ", typ: " + type + ", opis: " + description + ", data: " + achievement_date;
    }
}
