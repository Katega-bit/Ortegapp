package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.PasswordChange
import com.example.ortegapp_font.model.RegisterRequest
import com.example.ortegapp_font.model.RegisterResponse
import com.example.ortegapp_font.model.UserResponse
import com.example.ortegapp_font.service.LoginService
import retrofit2.Response
import retrofit2.create

class LoginRepository {

    private val retrofit = RetrofitHelper

    suspend fun login(username: String, password: String): UserResponse? {
        val response = retrofit.getRetrofit().create(LoginService::class.java)
            .login(LoginRequest(username, password))
        Log.e("LoginWorks", response.body().toString())
        if (response.isSuccessful){
            return response.body()!!

        }
        return null
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

    suspend fun register(username: String, password: String, verifyPassword: String, email : String, telefono : String, fullname : String) : RegisterResponse{
        val response = retrofit.getRetrofit().create(LoginService::class.java)
            .register(RegisterRequest(username, password, verifyPassword, email, telefono, fullname))
        Log.e("register", response.body().toString())
        return response.body()!!

    }

    suspend fun changePassword(password: String, repeatPassword : String, oldPassword : String,token : String): UserResponse? {

        val response =  retrofit.getRetrofit().create(LoginService::class.java)
            .changePassword(PasswordChange(oldPassword, password, repeatPassword),"Bearer "+token )
        Log.e("password", response.body().toString())
        if (response.isSuccessful){
            return response.body()!!

        }
        else{
           return null
        }
    }

}