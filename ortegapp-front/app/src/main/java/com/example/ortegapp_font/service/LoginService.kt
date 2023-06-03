package com.example.ortegapp_font.service

import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.RegisterRequest
import com.example.ortegapp_font.model.RegisterResponse
import com.example.ortegapp_font.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserResponse>

    @GET("/me")
    suspend fun userData(@Header("Authorization") token : String): Response<UserResponse>

    @POST("/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

}