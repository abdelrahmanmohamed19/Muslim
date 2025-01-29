package com.example.muslim.domain.repository

import android.content.Context
import org.json.JSONArray


interface AzkarRepository {

    suspend fun getMorningAzkar(context: Context): JSONArray

    suspend fun getEveningAzkar(context: Context): JSONArray
}