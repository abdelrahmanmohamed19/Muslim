package com.example.muslim.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.Repositories.HadithRepositorey
import com.example.muslim.model.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HadithViewModel : ViewModel() {
    val Repo = HadithRepositorey()

    val _Hadith = MutableStateFlow<List<Item>?>(null)
    val Hadith = _Hadith.asStateFlow()


    fun getHadith(context : Context){
        viewModelScope.launch {
            Repo.getHadith(context)
            _Hadith.value = Repo.HadithList.value
        }

    }
}