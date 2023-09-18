package com.example.muslim.data.repository

import android.content.Context
import com.example.muslim.data.remote.Api
import com.example.muslim.data.remote.ApiServices
import com.example.muslim.data.SessionManager
import com.example.muslim.data.remote.dto.Item
import com.example.muslim.domain.repository.HadithRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HadithRepositoryImpl : HadithRepository {

    private val api : ApiServices = Api("https://hadis-api-id.vercel.app/").retrofit

    override suspend fun getHadith(context : Context) : StateFlow<List<Item>>{

        val response = api.getHadith()
        val hadithList = MutableStateFlow(emptyList<Item>())

        if (response.isSuccessful) {
            val responseBody = response.body()
            val responseBodyData = responseBody?.items
            hadithList.value = responseBodyData !!
            SessionManager(context).cacheAhadeth(hadithList.value)
        }
        return hadithList
    }
}