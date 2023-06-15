package com.example.ortegapp_font.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("producto" ) var producto : String? = null,
   // @SerializedName("user"     ) var user     : String? = null,
    @SerializedName("avatar"   ) var avatar   : String? = null,
    @SerializedName("fullname" ) var fullname : String? = null,
    @SerializedName("mensaje"  ) var mensaje  : String? = null

)