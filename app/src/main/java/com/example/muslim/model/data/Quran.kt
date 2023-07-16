package com.example.muslim.model.data

import com.google.gson.annotations.SerializedName

data class Quran(
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
