package com.example.muslim.domain.usecase.home

import NetworkUtils
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.muslim.R
import com.example.muslim.data.remote.dto.Times
import com.example.muslim.data.repository.HomeRepositoryImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

class GetPrayerTimesUseCase {

    private val repo = HomeRepositoryImpl()
    private val network = NetworkUtils
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(android.icu.util.Calendar.YEAR)
    private val month = calendar.get(android.icu.util.Calendar.MONTH)
    private val day = calendar.get(android.icu.util.Calendar.DAY_OF_MONTH)
    private val date = "$day-$month-$year"

    suspend fun getPrayerTimes (snackBarAction : () -> Unit ,view: View , context : Context) : StateFlow<Times> {
        val prayerTimes = MutableStateFlow(Times("","","","","","",""))
        val snackBar =  Snackbar.make(view,"No Internet Connection", Snackbar.LENGTH_INDEFINITE)
        if (network.isInternetConnected()) {
            prayerTimes.value = repo.getPrayerTimes(date).value
        }
        else {
            val snackBarColor = ContextCompat.getColor(context,R.color.white)
            snackBar.setActionTextColor(snackBarColor).show()
            snackBar.setAction("Retry") {
                snackBarAction()
            }
            prayerTimes.value = Times("","No Internet Connection","","","","","")
        }
        return prayerTimes
    }
}