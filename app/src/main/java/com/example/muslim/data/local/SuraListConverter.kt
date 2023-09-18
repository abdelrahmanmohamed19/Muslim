package com.example.muslim.data.local

import androidx.room.TypeConverter
import com.example.muslim.data.remote.dto.QuranResponse
import com.google.gson.Gson

class SuraListConverter {
    @TypeConverter
    fun fromJson(json: String): QuranResponse {
        return Gson().fromJson(json, QuranResponse::class.java)
    }

    @TypeConverter
    fun toJson(list: QuranResponse): String {
        return Gson().toJson(list)
    }
}