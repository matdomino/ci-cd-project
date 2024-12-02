package com.atracker.achievement_tracker.model;

public class ResponseStatus {
    private int status;
    private String message;

    public ResponseStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return "Status: " + status + ": " + message;
    }
}
