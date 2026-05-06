package com.example.myapplication.models;

public class LoginResponse {

    public boolean success;
    public String message;
    public Result result;

    public static class Result {
        public String id;
        public String name;
        public String phoneNumber;
        public String role;
        public String accessToken;
        public String refreshToken;
        public String refreshTokenExpiryTime;
    }
}