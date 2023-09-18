package com.example.muslim.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.muslim.data.remote.dto.QuranResponse

@Entity(tableName = "quranTable")
data class Quran (
    @PrimaryKey
    val id : Int  ,
    val suraList : QuranResponse
)