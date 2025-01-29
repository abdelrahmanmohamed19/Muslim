package com.example.muslim.domain.repository

import android.content.Context
import org.json.JSONArray

interface QuranRepository {

    suspend fun getQuran(context: Context): JSONArray
}