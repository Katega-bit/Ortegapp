package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username"       ) val username       : String?,
    @SerializedName("password"       ) val password       : String?,
    @SerializedName("verifyPassword" ) val verifyPassword : String?,
    @SerializedName("email"          ) val email          : String? ,
    @SerializedName("telefono"       ) val telefono       : String?,
    @SerializedName("fullName"       ) val fullName       : String?
)