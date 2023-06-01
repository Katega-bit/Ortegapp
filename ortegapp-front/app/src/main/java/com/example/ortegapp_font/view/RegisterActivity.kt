package com.example.ortegapp_font.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToLogin()
    }

    fun navigateToLogin(){
        binding.backButton.setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}