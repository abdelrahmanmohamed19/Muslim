package com.example.muslim.presentation.home

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.remote.dto.Times
import com.example.muslim.domain.usecase.home.GetPrayerTimesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val useCase = GetPrayerTimesUseCase()
    private var _prayerTimes = MutableStateFlow(Times("","","","","","",""))
    val prayerTimes = _prayerTimes.asStateFlow()

    fun getPrayerTimes (view:View , context : Context){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPrayerTimes({ getPrayerTimes(view,context) },view,context).collect{
                _prayerTimes.value = it
            }
        }
    }
}