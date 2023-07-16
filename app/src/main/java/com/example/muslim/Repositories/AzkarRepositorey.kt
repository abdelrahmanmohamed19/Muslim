package com.example.muslim.Repositories

import android.content.Context
import com.example.muslim.model.MyAPi
import com.example.muslim.model.MyApiServices
import com.example.muslim.model.SessionManager
import com.example.muslim.model.data.AzkarList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AzkarRepositorey {

    val api : MyApiServices = MyAPi("https://www.hisnmuslim.com/").retrofit

    private val _AzkarList = MutableStateFlow<List<AzkarList>?> (null)
    val AzkarList = _AzkarList.asStateFlow()




    suspend fun getAzkar(context : Context) {
        val resoponse = api.getAzkar()
        if (resoponse.isSuccessful) {
            val responseBody = resoponse.body()
            val data = responseBody?.Azkar
            _AzkarList.value = data
            SessionManager(context).CacheAthkar(data !!)
        }
    }
}