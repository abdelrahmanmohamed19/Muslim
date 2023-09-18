package com.example.muslim.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface QuranDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSura (quran: Quran)

    @Query("SELECT * FROM quranTable WHERE id Like :id")
    suspend fun getSura (id: Int) : Quran
}