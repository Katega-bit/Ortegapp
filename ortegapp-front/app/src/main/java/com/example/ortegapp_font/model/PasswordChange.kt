package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class PasswordChange(
    @SerializedName("oldPassword"    ) var oldPassword    : String? = null,
    @SerializedName("newPassword"    ) var newPassword    : String? = null,
    @SerializedName("verifyPassword" ) var verifyPassword : String? = null
)
