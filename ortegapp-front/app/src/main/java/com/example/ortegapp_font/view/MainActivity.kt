package com.example.ortegapp_font.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.ortegapp_font.R
import com.example.ortegapp_font.databinding.ActivityMainBinding
import com.example.ortegapp_font.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.menuOption.observe(this) { fragment ->
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()

        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            vm.optionSelect(it)
            true

        }

    }

}