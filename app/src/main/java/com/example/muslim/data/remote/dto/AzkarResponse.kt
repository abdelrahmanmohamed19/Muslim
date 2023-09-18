package com.example.muslim.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AzkarResponse(
    @SerializedName("أذكار الصباح والمساء")
    val Azkar: List<AzkarList>
)

data class AzkarList(
    @SerializedName("ARABIC_TEXT")
    val content: String
)

