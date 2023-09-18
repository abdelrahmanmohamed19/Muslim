package com.example.muslim.data.repository

import com.example.muslim.data.remote.Api
import com.example.muslim.data.remote.ApiServices
import com.example.muslim.data.remote.dto.SuraList
import com.example.muslim.domain.repository.QuranRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuranRepositoryImpl : QuranRepository {

    private val api : ApiServices = Api("https://quranenc.com/api/v1/").retrofit

    override suspend fun getQuran(id : Int) : StateFlow<List<SuraList>> {

        val response = api.getQuran(id)
        val suraList = MutableStateFlow<List<SuraList>>(emptyList())

        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.result
            suraList.value = data !!
        }
        else {
            suraList.value = emptyList()
        }
        return suraList
    }
}