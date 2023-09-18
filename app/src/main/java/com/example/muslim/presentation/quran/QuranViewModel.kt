package com.example.muslim.presentation.quran

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.data.remote.dto.SuraList
import com.example.muslim.domain.usecase.quran.GetQuranUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {

    private val useCase = GetQuranUseCase()
    private var _quranList = MutableStateFlow(emptyList<SuraList>())
    val quranList = _quranList.asStateFlow()

    fun getQuran(id : Int,context: Context , view : View) {
      viewModelScope.launch(Dispatchers.IO) {
          useCase.getQuran(id,context,{ getQuran(id, context,view) },view ).collect{
             _quranList.value = it
          }
      }
    }
}
