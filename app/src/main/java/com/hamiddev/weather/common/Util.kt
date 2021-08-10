package com.hamiddev.weather.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
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

fun digitalDate(timestamp: Long): String {
    val persianDate = PersianDate(timestamp * 1000)
    val persianDateFormat = PersianDateFormat("H:m")
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

fun turnGPSOn(activity: Activity) {
    val provider: String =
        Settings.Secure.getString(activity.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
    if (!provider.contains("gps")) { //if gps is disabled
        val poke = Intent()
        poke.setClassName(
            "com.android.settings",
            "com.android.settings.widget.SettingsAppWidgetProvider"
        )
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE)
        poke.data = Uri.parse("3")
        activity.sendBroadcast(poke)
    }
}