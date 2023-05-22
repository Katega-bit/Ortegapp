package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class LoginRepository {

    private val retrofit = RetrofitHelper

    suspend fun login(username: String, password: String): UserResponse {
        val response = retrofit.getRetrofit().create(LoginService::class.java)
            .login(LoginRequest(username, password))
        Log.e("LoginWorks", response.body().toString())
        return response.body()!!
    }

    suspend fun userLogged(token: String): Boolean {
        val response = retrofit.getRetrofit().create(LoginService::class.java).userData(token)

        if (response.isSuccessful) {
            return true
        }
        return false
    }
}