package com.example.muslim.domain.repository

import com.example.muslim.data.local.dateTime.DateTime
import com.example.muslim.data.remote.dto.DatesTimesDto

interface DateTimeRepository {

   suspend fun getDateTime(): DatesTimesDto

   suspend fun insertDateTime(dateTime: DateTime)

   suspend fun deleteDateTime()

   suspend fun getDateTimeLocally(): List<DateTime>
}