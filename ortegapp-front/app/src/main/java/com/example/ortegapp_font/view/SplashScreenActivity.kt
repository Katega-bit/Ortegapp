package com.example.ortegapp_font.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ortegapp_font.R
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var repository : LoginRepository
    private lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        screenSplash.setKeepOnScreenCondition{true}
        repository = LoginRepository()
        tokenManager = TokenManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            loginValidator()
        }

    }

     suspend fun loginValidator() {

       var response = false
        if (!tokenManager.getToken().isNullOrEmpty()){
            response = repository.userLogged(tokenManager.getToken()!!)
        }

        if (response){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
         else{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}