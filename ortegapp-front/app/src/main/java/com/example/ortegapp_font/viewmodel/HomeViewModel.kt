package com.example.ortegapp_font.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ortegapp_font.core.TokenManager
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.repository.ProductoRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val repository = ProductoRepository()
    private lateinit var tokenManager: TokenManager

    private val _productoLiveData = MutableLiveData<ProductoResponse?>()
    val productoLiveData : LiveData<ProductoResponse?> = _productoLiveData



    fun fetchProductos(){
        viewModelScope.launch{
            val response =   repository.getProduct(tokenManager.getToken()!!)
            _productoLiveData.postValue(response.body())
        }
    }

    fun initToken(context: Context){
        tokenManager = TokenManager(context)
    }

    suspend fun likeProduct(id : Int){
        val response = repository.likeProduct(id, tokenManager.getToken()!!)
        Log.e("Like", response.body().toString())
    }

    private val _productoIdLiveData = MutableLiveData<Producto?>()
    val productoIdLiveData : LiveData<Producto?> = _productoIdLiveData

    fun productoByid(id : Int){
        viewModelScope.launch {
            val response = repository.getProductById(tokenManager.getToken()!!, id)
            _productoIdLiveData.postValue(response.body())
        }

    }


    suspend fun fetchMeLikes() : List<Producto>{
            val response =  repository.getMyLikes(tokenManager.getToken()!!)
        return response.body()?.content!!

    }


}