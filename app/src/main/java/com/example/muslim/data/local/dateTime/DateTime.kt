package com.example.muslim.data.local.dateTime

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataTime")
data class DateTime(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val todayDate: String,
    val hijiriData: String,
    val fajar: String,
    val duhur: String,
    val asar: String,
    val magharib: String,
    val isha: String
)
