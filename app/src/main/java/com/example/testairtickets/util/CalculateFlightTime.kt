package com.example.testairtickets.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

fun calculateFlightTime(departureString: String, arrivalString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    try {
        val departureDate: Date? = inputFormat.parse(departureString)
        val arrivalDate: Date? = inputFormat.parse(arrivalString)

        if (departureDate != null && arrivalDate != null) {
            val differenceMillis = abs(arrivalDate.time - departureDate.time)
            val hours = (differenceMillis / 3600000).toDouble()
            val roundedHours = hours.roundToInt()

            return "${roundedHours}ч в пути"
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return "Ошибка при вычислении времени полета"
}