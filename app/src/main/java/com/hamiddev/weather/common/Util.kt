package com.hamiddev.weather.common

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat

fun weatherIconLink(name: String) =
    "https://openweathermap.org/img/wn/$name@2x.png"

fun dayName(timestamp: Long): String {
    val persianDate = PersianDate(timestamp * 1000)
    val persianDateFormat = PersianDateFormat("l")
    if (persianDate.shDay == PersianDate().shDay)
        return "امروز"
    return persianDateFormat.format(persianDate)
}

fun fullDate(timestamp: Long): String {
    val persianDate = PersianDate(timestamp * 1000)
    val persianDateFormat = PersianDateFormat("d F Y")
    return persianDateFormat.format(persianDate)
}

fun formatTemp(temp: String): SpannableString {
    val label = "°C"
    val spannableString = SpannableString("$temp$label")
    spannableString.setSpan(
        RelativeSizeSpan(0.7f),
        spannableString.indexOf(label),
        spannableString.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}