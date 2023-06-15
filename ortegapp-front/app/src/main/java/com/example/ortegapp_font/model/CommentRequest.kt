package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class CommentRequest(
    @SerializedName("texto" ) val text : String?

)
