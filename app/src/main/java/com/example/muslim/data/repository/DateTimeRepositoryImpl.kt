package com.example.muslim.data.repository

import com.example.muslim.data.local.MuslimDatabase
import com.example.muslim.data.local.dateTime.DateTime
import com.example.muslim.data.remote.ApiServices
import com.example.muslim.data.remote.dto.DatesTimesDto
import com.example.muslim.domain.repository.DateTimeRepository
import retrofit2.Retrofit.Builder
import javax.inject.Inject

class DateTimeRepositoryImpl @Inject constructor(
    private val retrofitBuilder: Builder,
    private val database: MuslimDatabase
): DateTimeRepository {
    override suspend fun getDateTime(): DatesTimesDto {
        return retrofitBuilder
            .baseUrl("https://api.aladhan.com/")
            .build()
            .create(ApiServices::class.java)
            .getDatesTimes()
    }

    override suspend fun insertDateTime(dateTime: DateTime) {
        database.getDateTimeDao().insertDateTime(dateTime)
    }

    override suspend fun deleteDateTime() {
        database.getDateTimeDao().deleteDateTime()
    }

    override suspend fun getDateTimeLocally(): List<DateTime> {
        return database.getDateTimeDao().getDateTime()
    }
}