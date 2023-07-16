package com.example.muslim.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.Repositories.QuranRepositorey
import com.example.muslim.model.data.SuraList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {

    val Repo = QuranRepositorey()

    private val _QuranList = MutableStateFlow<List<SuraList>?>(null)
    val QuranList = _QuranList.asStateFlow()

    fun getQuran(id : Int ) {
        viewModelScope.launch {
            Repo.getQuran(id)
            _QuranList.value = Repo.QuranList.value
        }

    }
}