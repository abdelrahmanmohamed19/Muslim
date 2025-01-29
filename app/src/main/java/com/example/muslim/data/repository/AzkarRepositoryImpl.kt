package com.example.muslim.data.repository

import android.content.Context
import com.example.muslim.R
import com.example.muslim.domain.repository.AzkarRepository
import com.example.muslim.presentation.common.Utils
import org.json.JSONArray
import javax.inject.Inject

class AzkarRepositoryImpl @Inject constructor(): AzkarRepository {

    override suspend fun getMorningAzkar(context: Context): JSONArray {
        return Utils.readJsonObject(context, R.raw.morning_azkar) !!
    }

    override suspend fun getEveningAzkar(context: Context): JSONArray {
        return Utils.readJsonObject(context, R.raw.evening_azkar) !!
    }
}