package com.example.ortegapp_font.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.repository.ProductoRepository
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val repository = ProductoRepository()
    private lateinit var tokenManager: TokenManager

    private val _productoLikeLiveData = MutableLiveData<ProductoResponse?>()
    val productoLikeLiveData : LiveData<ProductoResponse?> = _productoLikeLiveData


    fun initToken(context: Context){
        tokenManager = TokenManager(context)
    }

    fun fetchMeLikes(){
        viewModelScope.launch {
           val response =  repository.getMyLikes(tokenManager.getToken()!!)
            _productoLikeLiveData.postValue(response.body())
        }
    }


}