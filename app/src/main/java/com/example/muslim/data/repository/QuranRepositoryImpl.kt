package com.example.muslim.data.repository

import android.content.Context
import com.example.muslim.R
import com.example.muslim.domain.repository.QuranRepository
import com.example.muslim.presentation.common.Utils
import org.json.JSONArray
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(): QuranRepository {

    override suspend fun getQuran(context: Context): JSONArray {
        return Utils.readJsonObject(context, R.raw.quran_explanation) !!
    }
}