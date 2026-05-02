package com.example.myapplication.models;

import java.util.List;

public class SecureDataResponse {
    private boolean success;
    private String message;
    private DataClass data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public DataClass getData() { return data; }

    public static class DataClass {
        private String title;
        private List<Item> items;

        public String getTitle() { return title; }
        public List<Item> getItems() { return items; }
    }

    public static class Item {
        private String id;
        private String name;
        private String description;

        public String getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
    }
}