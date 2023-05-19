package com.example.ortegapp_font.core

import android.content.Context

class TokenManager(context : Context) {
    private var prefs = context.getSharedPreferences("PREFS_TOKEN", Context.MODE_PRIVATE)

    fun saveToken(token: String){
        val editor = prefs.edit()
        editor.putString("USER_TOKEN", token )
        editor.apply()
    }

    fun getToken() : String?{
        return prefs.getString("USER_TOKEN", null)
    }

    fun deleteToke(){
        val editor = prefs.edit()
        editor.putString("USER_TOKEN", null)
        editor.apply()

    }
}