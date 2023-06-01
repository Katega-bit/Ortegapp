package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.UserResponse
import com.example.ortegapp_font.service.LoginService
import retrofit2.Response

class LoginRepository {

    private val retrofit = RetrofitHelper

    suspend fun login(username: String, password: String): UserResponse {
        val response = retrofit.getRetrofit().create(LoginService::class.java)
            .login(LoginRequest(username, password))
        Log.e("LoginWorks", response.body().toString())
        return response.body()!!
    }

    suspend fun userLogged(token: String): Boolean {
        val response = retrofit.getRetrofit().create(LoginService::class.java).userData("Bearer "+token)

        if (response.isSuccessful) {
            return true
        }
        return false
    }

    suspend fun userData(token: String): UserResponse? {
        val response = retrofit.getRetrofit().create(LoginService::class.java).userData("Bearer "+token)

        if (response.isSuccessful) {
            return response.body()!!
        }
            return null

    }

}