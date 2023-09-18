package com.example.muslim.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PrayerTimesResponse(
    @SerializedName("data")
    val `data`: Data? = Data()
)

data class Data(
    @SerializedName("date")
    val date: Date? = Date(),
    @SerializedName("timings")
    val timings: Timings? = Timings()
)

data class Timings(
    @SerializedName("Asr")
    val asr: String? = null,
    @SerializedName("Dhuhr")
    val dhuhr: String? = null,
    @SerializedName("Fajr")
    val fajr: String? = null,
    @SerializedName("Isha")
    val isha: String? = null,
    @SerializedName("Maghrib")
    val maghrib: String? = null,
)

data class Date(
    @SerializedName("hijri")
    val hijri: Hijri? = Hijri()
)

data class Hijri(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("month")
    val month: MonthX? = MonthX(),
)

data class MonthX(
    @SerializedName("en")
    val monthname : String? =null
)


