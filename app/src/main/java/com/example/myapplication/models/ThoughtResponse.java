package com.example.myapplication.models;

import java.util.List;

public class ThoughtResponse {
    private boolean success;
    private String message;
    private List<ThoughtData> data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<ThoughtData> getData() { return data; }

    public static class ThoughtData {
        private String id;
        private String title;
        private String description;
        private String category;

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getCategory() { return category; }
    }
}