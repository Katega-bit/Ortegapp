package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.LoginRequest
import com.example.ortegapp_font.model.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {

    //private lateinit var retrofit : RetrofitHelper

    suspend fun login(username:String, password:String):UserResponse?{
       val retrofit = RetrofitHelper.apiService
        val response = retrofit.login(LoginRequest(username, password))

        if (response.isSuccessful){
            return response.body()
                Log.i("Carlos", response.body().toString())
        }
        else{
          return null
        }
    }

}