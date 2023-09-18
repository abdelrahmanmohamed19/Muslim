package com.example.muslim.domain.repository

import android.content.Context
import com.example.muslim.data.remote.dto.Item
import kotlinx.coroutines.flow.StateFlow

interface HadithRepository {

    suspend fun getHadith(context : Context) : StateFlow<List<Item>>
}