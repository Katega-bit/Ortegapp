package com.example.ortegapp_font.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.ActivityRegisterBinding
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var repository : LoginRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        repository = LoginRepository()
        setContentView(binding.root)

        navigateToLogin()

        binding.registerButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    register()
                }
            }
        }

    }

    fun navigateToLogin(){
        binding.backButton.setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    suspend fun register(){
        val fullname = binding.etFullname.text.toString()
        val username =  binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()
        val telefono = binding.etPhone.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()

        val response =  repository.register(username, password, repeatPassword, email, telefono, fullname)
        Log.e("register", response.toString())

        if (response != null){
            val toast = Toast.makeText(applicationContext, "Usuario Registrado", Toast.LENGTH_LONG)
            toast.show()
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}