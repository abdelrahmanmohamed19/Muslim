package com.example.muslim.model.data


import com.google.gson.annotations.SerializedName

data class TimingsResponse(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("status")
    val status: String? = ""
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
    @SerializedName("Firstthird")
    val firstthird: String? = null,
    @SerializedName("Imsak")
    val imsak: String? = null,
    @SerializedName("Isha")
    val isha: String? = null,
    @SerializedName("Lastthird")
    val lastthird: String? = null,
    @SerializedName("Maghrib")
    val maghrib: String? = null,
    @SerializedName("Midnight")
    val midnight: String? = null,
    @SerializedName("Sunrise")
    val sunrise: String? = null,
    @SerializedName("Sunset")
    val sunset: String? = null
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


