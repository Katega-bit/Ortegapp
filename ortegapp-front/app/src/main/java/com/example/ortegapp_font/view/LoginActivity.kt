package com.example.ortegapp_font.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.ActivityLoginBinding
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var bindind: ActivityLoginBinding
    private lateinit var repository: LoginRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityLoginBinding.inflate(layoutInflater)
        repository = LoginRepository()
        setContentView(bindind.root)


        bindind.loginButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                var username = bindind.etEmail
                var password = bindind.etPassword
                repository.login(username.toString(), password.toString())

            }
        }
    }
}