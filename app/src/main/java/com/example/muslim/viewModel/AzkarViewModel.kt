package com.example.muslim.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muslim.Repositories.AzkarRepositorey
import com.example.muslim.model.SessionManager
import com.example.muslim.model.data.AzkarList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AzkarViewModel : ViewModel() {

    val Repo = AzkarRepositorey()

    private val _AzkarList = MutableStateFlow<List<AzkarList>?> (null)
    val AzkarList = _AzkarList.asStateFlow()

    fun getAzkar(context : Context) {
        viewModelScope.launch {
            Repo.getAzkar(context)
            _AzkarList.value = Repo.AzkarList.value
    }}}