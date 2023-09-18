package com.example.muslim.domain.usecase.hadith

import NetworkUtils
import android.content.Context
import com.example.muslim.data.SessionManager
import com.example.muslim.data.remote.dto.Item
import com.example.muslim.data.repository.HadithRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetHadithUseCase {

    private val repo = HadithRepositoryImpl()
    private val network = NetworkUtils

    suspend fun getHadith(context : Context) : StateFlow<List<Item>> {
        val hadith = MutableStateFlow(emptyList<Item>())
        val cachedData = SessionManager(context)

        if (network.isInternetConnected()) {
            repo.getHadith(context)
            hadith.value = repo.getHadith(context).value
            cachedData.cacheAhadeth(hadith.value)
            }
        else {
            hadith.value = cachedData.getCachedAhadeth()
        }
        return hadith
    }
}