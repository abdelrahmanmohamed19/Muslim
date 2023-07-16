package com.example.muslim.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.Repositories.TimingRepositorey
import com.example.muslim.model.data.Times
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel : ViewModel() {

    val Repo = TimingRepositorey()
    private val _Data = MutableStateFlow<Times?>(null)
    val MyData : StateFlow<Times?>  = _Data
    val calendar = Calendar.getInstance()
    val year = calendar.get(android.icu.util.Calendar.YEAR)
    val month = calendar.get(android.icu.util.Calendar.MONTH)
    val day = calendar.get(android.icu.util.Calendar.DAY_OF_MONTH)
    val date = "${day.toString()}-${month.toString()}-${year.toString()}"

    init {
        getTimings()
    }


    fun getTimings () {
        viewModelScope.launch {
            Repo.getTimings(date)
            _Data.value = Repo.TimesData.value
        }
    }
}