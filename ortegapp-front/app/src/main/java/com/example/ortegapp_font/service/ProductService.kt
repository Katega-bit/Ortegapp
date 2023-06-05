package com.example.ortegapp_font.service

import com.example.ortegapp_font.model.CommentResponse
import com.example.ortegapp_font.model.Producto
import com.example.ortegapp_font.model.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("producto/")
    suspend fun getProudct(@Header("Authorization") token: String) : Response<ProductoResponse>

    @GET("producto/{id}")
    suspend fun getProudctById(@Header("Authorization") token: String, @Path("id") id : Int) : Response<Producto>

    @GET("/melikes/")
    suspend fun getMeLikes(@Header("Authorization") token: String): Response<ProductoResponse>

    @POST("producto/like/{id}")
    suspend fun likeProduct(@Path("id")id : Int, @Header("Authorization") token: String) : Response<Producto>

    @POST("/producto/{id}/comentario/")
    suspend fun commentProduct(@Path("id")id : Int, @Header("Authorization") token: String) : Response<Producto>
}