package com.example.ortegapp_font.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.ortegapp_font.R
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.databinding.ActivityLoginBinding
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var bindind: ActivityLoginBinding
    private lateinit var repository: LoginRepository
    private lateinit var tokenManager : TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityLoginBinding.inflate(layoutInflater)
        repository = LoginRepository()
        tokenManager = TokenManager(this)
        setContentView(bindind.root)

        navigateToRegister()
        bindind.loginButton.setOnClickListener {
        CoroutineScope(Dispatchers.Main).launch {

                login()
            }
        }


    }
    suspend fun login(){

                val username = bindind.etEmail
                val password = bindind.etPassword
                val response =repository.login(username.text.toString(), password.text.toString())
                val token = response.token.toString()
                tokenManager.saveToken(token)

                if (tokenManager.getToken()==response.token){
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
    }

    fun navigateToRegister(){
        bindind.tvRegister.setOnClickListener {
        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        }

    }
}