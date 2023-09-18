package com.example.muslim.data.repository

import com.example.muslim.data.remote.Api
import com.example.muslim.data.remote.ApiServices
import com.example.muslim.data.remote.dto.Times
import com.example.muslim.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Locale

class HomeRepositoryImpl : HomeRepository {

    private val api : ApiServices = Api("https://api.aladhan.com/v1/").retrofit


    override fun convertTo12HourFormat(time24Hour: String): String {
        val sdf24Hour = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf12Hour = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val time24HourParsed = sdf24Hour.parse(time24Hour)
        return sdf12Hour.format(time24HourParsed !!)
    }

    override suspend fun getPrayerTimes(date : String) : StateFlow<Times> {

        val islamicPrayerTimes = MutableStateFlow(Times("","","","","","",""))

         val response = api.getPrayerTimes(date)
          if(response.isSuccessful){
              val responseBody = response.body()
              val responseBodyData = responseBody?.data?.date?.hijri
              val date = responseBodyData?.date
              val month = responseBodyData?.month?.monthname
              val prayerTimes = responseBody?.data?.timings
              val fajr = convertTo12HourFormat(prayerTimes?.fajr.toString())
              val dhuhr =convertTo12HourFormat(prayerTimes?.dhuhr.toString())
              val asr = convertTo12HourFormat(prayerTimes?.asr.toString())
              val maghrib = convertTo12HourFormat(prayerTimes?.maghrib.toString())
              val isha = convertTo12HourFormat(prayerTimes?.isha.toString())
              islamicPrayerTimes.value = Times(date!!,month!!,fajr,dhuhr,asr,maghrib,isha)
          }
        return islamicPrayerTimes
    }
}