package com.example.ortegapp_font.repository

import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserResponse>

    @GET("me")
    suspend fun userData(@Header("Authorization") token : String): Response<UserResponse>

}