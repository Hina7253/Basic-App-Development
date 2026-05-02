package com.example.myapplication.models;

public class Thought {
    private String id;
    private String title;
    private String description;
    private String category;
    private String author;
    private int likes;

    public Thought(String id, String title, String description, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getAuthor() { return author; }
    public int getLikes() { return likes; }
}