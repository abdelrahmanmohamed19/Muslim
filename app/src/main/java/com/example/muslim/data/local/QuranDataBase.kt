package com.example.muslim.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Quran::class] , version = 1)
@TypeConverters(SuraListConverter::class)
abstract class QuranDataBase : RoomDatabase() {

    abstract fun getDao () : QuranDao
}