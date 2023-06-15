package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("content"       ) var content       : ArrayList<Producto> = arrayListOf(),
    @SerializedName("totalElements" ) var totalElements : Int?,
    @SerializedName("totalPages"    ) var totalPages    : Int?,
    @SerializedName("page"          ) var page          : Int?
)
