package com.example.myapplication.models;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private String icon;
    private int itemCount;
    private List<Thought> thoughts;

    public Category(String id, String name, int itemCount) {
        this.id = id;
        this.name = name;
        this.itemCount = itemCount;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getItemCount() { return itemCount; }
    public List<Thought> getThoughts() { return thoughts; }
}