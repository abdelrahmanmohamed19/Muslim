package com.example.muslim.Repositories

import android.util.Log
import com.example.muslim.model.MyAPi
import com.example.muslim.model.MyApiServices
import com.example.muslim.model.data.Date
import com.example.muslim.model.data.Times
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Locale

class TimingRepositorey  {

    val api : MyApiServices = MyAPi("https://api.aladhan.com/v1/").retrofit
    val _TimesData = MutableStateFlow<Times?>(null)
    val TimesData : StateFlow<Times?> = _TimesData



    fun convertTo12HourFormat(time24Hour: String): String {
        val sdf24Hour = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf12Hour = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val time24HourParsed = sdf24Hour.parse(time24Hour)
        return sdf12Hour.format(time24HourParsed)
    }

    suspend fun getTimings (date : String) {
         val response = api.getTimings(date)
          if(response.isSuccessful){
              val ResponseBody = response.body()
              val Date = ResponseBody?.data?.date?.hijri
              val date = Date?.date
              val month = Date?.month?.monthname
              val Timings = ResponseBody?.data?.timings
              val fajr = convertTo12HourFormat(Timings?.fajr.toString())
              val dhuhr =convertTo12HourFormat(Timings?.dhuhr.toString())
              val asr = convertTo12HourFormat(Timings?.asr.toString())
              val maghrib = convertTo12HourFormat(Timings?.maghrib.toString())
              val isha = convertTo12HourFormat(Timings?.isha.toString())
              val data = Times(date!!,month!!,fajr,dhuhr,asr,maghrib,isha)
              _TimesData.value = data
          }
//        Log.i("AbdoRepo","${TimesData.value.toString()}")
    }
}