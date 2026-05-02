package com.example.myapplication.network;

import com.example.myapplication.models.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // Login
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    // Register
    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    // Get Categories
    @GET("api/categories")
    Call<CategoryResponse> getCategories(@Header("Authorization") String token);

    // Get Thoughts by Category
    @GET("api/thoughts/{categoryId}")
    Call<ThoughtResponse> getThoughtsByCategory(
            @Header("Authorization") String token,
            @Path("categoryId") String categoryId
    );

    // Get Featured Thoughts
    @GET("api/featured")
    Call<ThoughtResponse> getFeaturedThoughts(@Header("Authorization") String token);

    // Logout
    @POST("api/logout")
    Call<LoginResponse> logout(@Header("Authorization") String token);
}

class CategoryResponse {
    public boolean success;
    public java.util.List<Category> data;
    public String message;
}

class ThoughtResponse {
    public boolean success;
    public java.util.List<Thought> data;
    public String message;
}