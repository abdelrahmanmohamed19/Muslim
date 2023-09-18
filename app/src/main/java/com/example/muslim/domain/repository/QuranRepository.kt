package com.example.muslim.domain.repository

import com.example.muslim.data.remote.dto.SuraList
import kotlinx.coroutines.flow.Flow

interface QuranRepository {

    suspend fun getQuran(id : Int) : Flow<List<SuraList>>

}