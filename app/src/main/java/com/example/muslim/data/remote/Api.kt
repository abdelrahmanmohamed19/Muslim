package com.example.muslim.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api (url : String) {

    val retrofit  = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)
}