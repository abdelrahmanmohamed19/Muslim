package com.example.muslim.Repositories

import android.content.Context
import com.example.muslim.model.MyAPi
import com.example.muslim.model.MyApiServices
import com.example.muslim.model.SessionManager
import com.example.muslim.model.data.SuraList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuranRepositorey {

    val api : MyApiServices = MyAPi("https://quranenc.com/api/v1/").retrofit

    private val _QuranList = MutableStateFlow<List<SuraList>?>(null)

    val QuranList = _QuranList.asStateFlow()


    suspend fun getQuran(id : Int) {
        val response = api.getQuran(id)
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.result
            _QuranList.value = data
        }
    }
}