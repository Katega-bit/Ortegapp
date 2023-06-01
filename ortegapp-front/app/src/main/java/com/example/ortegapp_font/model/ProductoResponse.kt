package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("content"       ) var content       : ArrayList<Producto> = arrayListOf(),
    @SerializedName("totalElements" ) var totalElements : Int?               = null,
    @SerializedName("totalPages"    ) var totalPages    : Int?               = null,
    @SerializedName("page"          ) var page          : Int?               = null
)
