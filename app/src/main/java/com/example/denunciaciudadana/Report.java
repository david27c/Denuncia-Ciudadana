package com.example.denunciaciudadana;

public class Report {
    private String title;
    private String description;
    private String date;

    public Report(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
}