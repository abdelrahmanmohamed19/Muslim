package com.example.muslim.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AhadethResponse(
    @SerializedName("items")
    val items: List<Item>? = null,
)

data class Item(
    @SerializedName("arab")
    val arab: String? = null,
)
