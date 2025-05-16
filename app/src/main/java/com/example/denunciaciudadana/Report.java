package com.example.denunciaciudadana;

import java.io.Serializable;

public class Report implements Serializable {
    private String title;
    private String description;
    private String date;
    private String location;
    private String status;

    public Report(String title, String description, String date, String location, String status) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.status = status;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setStatus(String status) { this.status = status; }
}