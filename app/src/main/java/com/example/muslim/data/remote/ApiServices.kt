package com.example.muslim.data.remote

import com.example.muslim.data.remote.dto.AhadethResponse
import com.example.muslim.data.remote.dto.AzkarResponse
import com.example.muslim.data.remote.dto.PrayerTimesResponse
import com.example.muslim.data.remote.dto.QuranResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("timingsByCity?city=cairo&country=egypt")
    suspend fun getPrayerTimes ( @Query("date")  date : String ) : Response<PrayerTimesResponse>

    @GET("hadith/abu-dawud?page=1&limit=30")
    suspend fun getHadith() : Response<AhadethResponse>

    @GET("api/ar/27.json")
    suspend fun getAzkar() : Response<AzkarResponse>

    @GET("translation/sura/arabic_moyassar/{id}")
    suspend fun getQuran(@Path("id") id : Int) : Response<QuranResponse>
}