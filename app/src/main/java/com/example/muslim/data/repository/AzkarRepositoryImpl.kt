package com.example.muslim.data.repository

import android.content.Context
import com.example.muslim.data.remote.Api
import com.example.muslim.data.remote.ApiServices
import com.example.muslim.data.SessionManager
import com.example.muslim.data.remote.dto.AzkarList
import com.example.muslim.domain.repository.AzkarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AzkarRepositoryImpl : AzkarRepository {

    private val api : ApiServices = Api("https://www.hisnmuslim.com/").retrofit

    override suspend fun getAzkar(context : Context) : StateFlow<List<AzkarList>> {

        val response = api.getAzkar()
        val azkarList = MutableStateFlow(emptyList<AzkarList>())

        if (response.isSuccessful) {
            val responseBody = response.body()
            val responseBodyData = responseBody?.Azkar
            azkarList.value = responseBodyData !!
            SessionManager(context).cacheAzkar(azkarList.value)
        }
        return azkarList
    }
}