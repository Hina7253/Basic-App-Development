package com.example.myapplication.models;

import java.util.List;

public class CategoryResponse {
    private boolean success;
    private String message;
    private List<CategoryData> data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<CategoryData> getData() { return data; }

    public static class CategoryData {
        private String id;
        private String name;
        private String emoji;
        private int count;

        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmoji() { return emoji; }
        public int getCount() { return count; }
    }
}