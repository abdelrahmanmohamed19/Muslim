package com.example.muslim.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.common.ApiResult
import com.example.muslim.domain.usecase.GetDateTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getDateTimeUseCase: GetDateTimeUseCase): ViewModel() {

    var homeUIState by mutableStateOf<HomeUIState?>(null)
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatesTimes () {
        viewModelScope.launch{
            getDateTimeUseCase.getDatesTimes().collect{ response ->
                homeUIState = when(response) {
                    is ApiResult.Success -> {
                        Log.d("DSdsadsdsa", response.data.toString())
                        HomeUIState(data = response.data)
                    }
                    is ApiResult.Error -> {
                        Log.d("DSdsadsdsa", response.errorMessage.toString())
                        HomeUIState(errorMessage = response.errorMessage)
                    }
                    is ApiResult.Loading -> {
                        Log.d("DSdsadsdsa", "Loading....")
                        HomeUIState(isLoading = true)
                    }
                }
            }
        }
    }
}