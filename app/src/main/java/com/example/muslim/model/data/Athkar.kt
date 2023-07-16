package com.example.muslim.model.data


import com.google.gson.annotations.SerializedName

data class Athkar(
    @SerializedName("أذكار الصباح والمساء")
    val Azkar: List<AzkarList>
)

data class AzkarList(
    @SerializedName("ARABIC_TEXT")
    val content: String
)

