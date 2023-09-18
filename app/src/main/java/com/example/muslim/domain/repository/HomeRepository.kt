package com.example.muslim.domain.repository

import com.example.muslim.data.remote.dto.Times
import kotlinx.coroutines.flow.StateFlow

interface HomeRepository {

   fun convertTo12HourFormat(time24Hour: String): String

   suspend fun getPrayerTimes (date : String) : StateFlow<Times>

}