package com.example.muslim.presentation.azkar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.repository.AzkarRepositoryImpl
import com.example.muslim.data.remote.dto.AzkarList
import com.example.muslim.domain.usecase.azkar.GetAzkarUseCase
import com.example.muslim.domain.usecase.hadith.GetHadithUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AzkarViewModel : ViewModel() {

    private val useCase = GetAzkarUseCase()
    private var _azkarList = MutableStateFlow(emptyList<AzkarList>())
    val azkarList = _azkarList.asStateFlow()

    fun getAzkar(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAzkar(context).collect{
                _azkarList.value = it
            }
        }
    }
}