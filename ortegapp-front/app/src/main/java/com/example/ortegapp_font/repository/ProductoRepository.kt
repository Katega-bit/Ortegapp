package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.service.ProductService
import retrofit2.Response
import retrofit2.create

class ProductoRepository {

    val retrofit = RetrofitHelper.getRetrofit().create(ProductService::class.java)

    suspend fun getProduct(token : String): Response<ProductoResponse>{
        val response = retrofit.getProudct("Bearer $token")
        Log.e("code ", response.code().toString())
        return response
    }

    suspend fun likeProduct(id : Int, token : String) : Response<Producto>{
        val response = retrofit.likeProduct(id, "Bearer $token")
        Log.e("Like", response.body().toString())
        return response
    }

    suspend fun getProductById(token : String, id : Int) :  Response<Producto>{
        val response = retrofit.getProudctById("Bearer $token", id)
        Log.e("producto", response.body().toString())
        return response
    }

    suspend fun getMyLikes(token : String) : Response<ProductoResponse>{
        val response = retrofit.getMeLikes("Bearer $token")
        Log.e("Me Likes", response.body().toString())
        return response
    }
}