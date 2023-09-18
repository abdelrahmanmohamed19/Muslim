package com.example.muslim.data

import android.content.Context
import com.example.muslim.data.remote.dto.AzkarList
import com.example.muslim.data.remote.dto.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager (val context : Context) {

    private val sharedPreferences = context.getSharedPreferences("YourCacheName", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun cacheAzkar( data : List<AzkarList>) {
          sharedPreferences.edit().putString("AzkarList",gson.toJson(data)).apply()
    }

    fun getCachedAzkar(): List<AzkarList> {
        val json = sharedPreferences.getString("AzkarList", null)
        val type = object : TypeToken<List<AzkarList>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun cacheAhadeth(data : List<Item>) {
        sharedPreferences.edit().putString("AhadethList",gson.toJson(data)).apply()
    }

    fun getCachedAhadeth(): List<Item> {
        val json = sharedPreferences.getString("AhadethList", null)
        val type = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

}