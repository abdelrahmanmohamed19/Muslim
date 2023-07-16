package com.example.muslim.model

import com.example.muslim.model.data.Athkar
import com.example.muslim.model.data.Quran
import com.example.muslim.model.data.TimingsResponse
import com.example.muslim.model.data.hadesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MyApiServices {

    @GET("timingsByCity?city=cairo&country=egypt")
    suspend fun getTimings (
        @Query("date")  date : String
    ) : Response<TimingsResponse>

    @GET("hadith/abu-dawud?page=1&limit=30")
    suspend fun getAhadeth() : Response<hadesResponse>

    @GET("api/ar/27.json")
    suspend fun getAzkar() : Response<Athkar>

    @GET("translation/sura/arabic_moyassar/{id}")
    suspend fun getQuran(@Path("id") id : Int) : Response<Quran>
}