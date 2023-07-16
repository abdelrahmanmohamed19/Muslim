package com.example.muslim.model.data

import com.google.gson.annotations.SerializedName

data class hadesResponse(
    @SerializedName("items")
    val items: List<Item?>? = null,
)
data class Item(
    @SerializedName("arab")
    val arab: String? = null,
)
