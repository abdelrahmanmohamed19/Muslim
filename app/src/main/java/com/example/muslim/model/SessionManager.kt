package com.example.muslim.model

import android.content.Context
import com.example.muslim.model.data.Athkar
import com.example.muslim.model.data.AzkarList
import com.example.muslim.model.data.Item
import com.example.muslim.model.data.SuraList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager (val context : Context) {

    val sharedPreferences = context.getSharedPreferences("YourCacheName", Context.MODE_PRIVATE)
    val gson = Gson()

    fun CacheAthkar(Data : List<AzkarList>) {
          sharedPreferences.edit().putString("AthkarList",gson.toJson(Data)).apply()
    }

    fun getCachedAthkar(): List<AzkarList> {
        val json = sharedPreferences.getString("AthkarList", null)
        val type = object : TypeToken<List<AzkarList>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun CacheSura(Data : List<SuraList>) {
        sharedPreferences.edit().putString("SuraList",gson.toJson(Data)).apply()
    }

    fun getCachedSura(): List<SuraList> {
        val json = sharedPreferences.getString("SuraList", null)
        val type = object : TypeToken<List<SuraList>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun CacheHadith(Data : List<Item>) {
        sharedPreferences.edit().putString("HadithList",gson.toJson(Data)).apply()
    }

    fun getCachedHadith(): List<Item> {
        val json = sharedPreferences.getString("HadithList", null)
        val type = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

}