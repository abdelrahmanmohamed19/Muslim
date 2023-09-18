package com.example.muslim.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuranResponse(
     @SerializedName("result")
     val result : List<SuraList>
)

data class SuraList(
    @SerializedName("arabic_text")
    val text : String,
    @SerializedName("aya")
    val aya : String,
    @SerializedName("translation")
    val translation : String
)
