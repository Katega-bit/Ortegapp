package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id"          ) var id          : Int?              = null,
    @SerializedName("nombre"      ) var nombre      : String?           = null,
    @SerializedName("foto"        ) var foto        : String?           = null,
    @SerializedName("tipo"        ) var tipo        : String?           = null,
    @SerializedName("descripcion" ) var descripcion : String?           = null,
    @SerializedName("precio"      ) var precio      : Double?           = null,
    @SerializedName("likes"       ) var likes       : ArrayList<String> = arrayListOf(),
    @SerializedName("comentarios" ) var comentarios : ArrayList<String> = arrayListOf()
)