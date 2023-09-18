package com.example.muslim.domain.repository

import android.content.Context
import com.example.muslim.data.remote.dto.AzkarList
import kotlinx.coroutines.flow.StateFlow

interface AzkarRepository {

    suspend fun getAzkar(context : Context) : StateFlow<List<AzkarList>>
}