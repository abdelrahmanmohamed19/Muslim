package com.example.muslim.Repositories

import android.content.Context
import android.util.Log
import com.example.muslim.model.MyAPi
import com.example.muslim.model.MyApiServices
import com.example.muslim.model.SessionManager
import com.example.muslim.model.data.Item
import com.example.muslim.model.data.hadesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HadithRepositorey {

    val api : MyApiServices = MyAPi("https://hadis-api-id.vercel.app/").retrofit

    private val _HadithList = MutableStateFlow<List<Item>?> (null)
    val HadithList = _HadithList.asStateFlow()


    suspend fun getHadith(context : Context){
        val response = api.getAhadeth()
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.items
            _HadithList.value = data as List<Item>
            SessionManager(context).CacheHadith(data)
        }
    }
}