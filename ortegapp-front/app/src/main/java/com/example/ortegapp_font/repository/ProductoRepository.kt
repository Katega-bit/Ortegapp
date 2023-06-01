package com.example.ortegapp_font.repository

import com.example.ortegapp_font.core.RetrofitHelper
import com.example.ortegapp_font.service.ProductService

class ProductoRepository {

    val retrofit = RetrofitHelper

    suspend fun getProduct(){
        retrofit.getRetrofit().create(ProductService::class.java).getProudct()
    }
}