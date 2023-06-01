package com.example.ortegapp_font.service

import com.example.ortegapp_font.model.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("/producto/")
    suspend fun getProudct() : Response<ProductoResponse>
}