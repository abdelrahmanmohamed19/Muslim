package com.example.muslim.presentation.quran

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.common.ApiResult
import com.example.muslim.domain.usecase.GetQuranUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(private val getQuranUseCase: GetQuranUseCase) : ViewModel() {

    var quranUIState by mutableStateOf<QuranUIState?>(null)
        private set

    fun getQuran(suraNumber: Int, context: Context, withExplanation: Boolean) {
        viewModelScope.launch{
            getQuranUseCase.getQuran(suraNumber, context, withExplanation).collect{ response ->
                quranUIState = when(response) {
                    is ApiResult.Success -> QuranUIState(data = response.data)
                    is ApiResult.Error -> QuranUIState(errorMessage = response.errorMessage)
                    is ApiResult.Loading -> QuranUIState(isLoading = true)
                }
            }
        }
    }
}
