package com.example.muslim.presentation.hadith

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.SessionManager
import com.example.muslim.data.repository.HadithRepositoryImpl
import com.example.muslim.data.remote.dto.Item
import com.example.muslim.domain.usecase.hadith.GetHadithUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HadithViewModel : ViewModel() {

    private val useCase = GetHadithUseCase()
    private var _hadith = MutableStateFlow(emptyList<Item>())
    val hadith =_hadith.asStateFlow()

     fun getHadith(context : Context){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getHadith(context).collect{
                _hadith.value = it
            }
        }
    }

}