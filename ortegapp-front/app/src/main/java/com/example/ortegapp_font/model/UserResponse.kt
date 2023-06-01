package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id"           ) val id           : String? ,
    @SerializedName("username"     ) val username     : String? ,
    @SerializedName("avatar"       ) val avatar       : String? ,
    @SerializedName("fullName"     ) val fullName     : String? ,
    @SerializedName("createdAt"    ) val createdAt    : String? ,
    @SerializedName("token"        ) val token        : String? ,
    @SerializedName("refreshToken" ) val refreshToken : String?

)
