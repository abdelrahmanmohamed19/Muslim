package com.example.muslim.domain.usecase.azkar

import NetworkUtils
import android.content.Context
import com.example.muslim.data.SessionManager
import com.example.muslim.data.remote.dto.AzkarList
import com.example.muslim.data.repository.AzkarRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetAzkarUseCase {

    private val repo = AzkarRepositoryImpl()
    private val network = NetworkUtils

    suspend fun getAzkar(context : Context) : StateFlow<List<AzkarList>> {
        val cachedData = SessionManager(context)

        val azkarList = MutableStateFlow(emptyList<AzkarList>())
        if (network.isInternetConnected()) {
            repo.getAzkar(context)
            azkarList.value = repo.getAzkar(context).value
            cachedData.cacheAzkar(azkarList.value)
        }
        else {
            azkarList.value = cachedData.getCachedAzkar()
        }
        return azkarList
    }
}