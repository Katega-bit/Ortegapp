package com.example.ortegapp_font.viewmodel

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ortegapp_font.R
import com.example.ortegapp_font.view.HomeFragment
import com.example.ortegapp_font.view.ProfileFragment
import com.example.ortegapp_font.view.FavoriteFragment

class MainViewModel : ViewModel() {

    private val _menuOption =  MutableLiveData<Fragment>(HomeFragment())
    val menuOption : LiveData<Fragment> get() = _menuOption

    fun optionSelect(item : MenuItem){
        _menuOption.value = when(item.itemId){
            R.id.home -> HomeFragment()
            R.id.favorite -> FavoriteFragment()
            R.id.profile -> ProfileFragment()
            else -> {HomeFragment()}
        }
    }
}