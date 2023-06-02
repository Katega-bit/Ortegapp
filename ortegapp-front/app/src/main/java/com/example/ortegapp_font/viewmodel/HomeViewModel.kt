package com.example.ortegapp_font.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.paging.ProductoPagingSource
import com.example.ortegapp_font.repository.ProductoRepository

class HomeViewModel : ViewModel() {
    private lateinit var token : TokenManager
    private val repository = ProductoRepository()

    val productoList = Pager(PagingConfig(1)){
        ProductoPagingSource(repository, token.getToken()!!)
    }.flow.cachedIn(viewModelScope)



    fun tokenManagerInit(context: Context){
        token = TokenManager(context)
    }

}