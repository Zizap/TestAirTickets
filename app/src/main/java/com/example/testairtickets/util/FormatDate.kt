package com.example.testairtickets.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatDate(calendar: Calendar): String {
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val dayFormat = SimpleDateFormat("E", Locale.getDefault())

    val dateString = dateFormat.format(calendar.time)
    val dayString = dayFormat.format(calendar.time)

    return "$dateString, $dayString"
}

fun formatTime(string: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    try {
        val date: Date? = inputFormat.parse(string)
        if (date != null) {
            return outputFormat.format(date)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}