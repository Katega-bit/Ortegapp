package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id"        ) var id        : String? = null,
    @SerializedName("username"  ) var username  : String? = null,
    @SerializedName("avatar"    ) var avatar    : String? = null,
    @SerializedName("fullName"  ) var fullName  : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null
)
