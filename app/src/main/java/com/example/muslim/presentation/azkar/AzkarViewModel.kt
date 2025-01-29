package com.example.muslim.presentation.azkar

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.common.ApiResult
import com.example.muslim.domain.usecase.GetEveningAzkarUseCase
import com.example.muslim.domain.usecase.GetMorningAzkarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AzkarViewModel @Inject constructor(
    private val morningAzkarUseCase: GetMorningAzkarUseCase,
    private val eveningAzkarUseCase: GetEveningAzkarUseCase
) : ViewModel() {

    var morningAzkarUIState by mutableStateOf<AzkarUIState?>(null)
        private set

    var eveningAzkarUIState by mutableStateOf<AzkarUIState?>(null)
        private set

    suspend fun getMorningAzkar (context: Context) {
        viewModelScope.launch{
            morningAzkarUseCase.getMorningAzkar(context).collect { response ->
                morningAzkarUIState = when(response) {
                    is ApiResult.Success -> AzkarUIState(data = response.data)
                    is ApiResult.Error -> AzkarUIState(errorMessages = response.errorMessage)
                    is ApiResult.Loading -> AzkarUIState(isLoading = true)
                }
            }
        }
    }

    suspend fun getEveningAzkar (context: Context) {
        viewModelScope.launch{
            eveningAzkarUseCase.getEveningAzkar(context).collect { response ->
                eveningAzkarUIState = when(response) {
                    is ApiResult.Success -> AzkarUIState(data = response.data)
                    is ApiResult.Error -> AzkarUIState(errorMessages = response.errorMessage)
                    is ApiResult.Loading -> AzkarUIState(isLoading = true)
                }
            }
        }
    }
}