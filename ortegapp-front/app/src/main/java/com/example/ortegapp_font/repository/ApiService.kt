package com.example.ortegapp_font.repository

import retrofit2.Call

import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query
import javax.security.auth.callback.Callback

interface ApiService {

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserResponse>

}