package com.example.ortegapp_font.repository

import android.util.Log
import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.model.ProductoResponse
import com.example.ortegapp_font.service.ProductService
import retrofit2.Response

class ProductoRepository {

    val retrofit = RetrofitHelper

    suspend fun getProduct(page : Int, token : String): Response<ProductoResponse>{
        val response = retrofit.getRetrofit().create(ProductService::class.java).getProudct(page, "Bearer $token")
        Log.e("code ", response.code().toString())
        return response
    }
}