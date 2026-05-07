package com.example.myapplication.network;

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.RegisterRequest;
import com.example.myapplication.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // REGISTER API
    @POST("api/Auth/student/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    // LOGIN API
    @POST("api/Auth/student/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}