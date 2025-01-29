package com.example.muslim.data.remote

import com.example.muslim.data.remote.dto.DatesTimesDto
import retrofit2.http.GET

interface ApiServices{

    @GET("v1/timingsByCity?city=Cairo&country=Eg")
    suspend fun getDatesTimes(): DatesTimesDto
}