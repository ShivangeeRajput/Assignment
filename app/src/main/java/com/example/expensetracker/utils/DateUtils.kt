package com.example.expensetracker.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    fun parseDate(dateString: String): Date? {
        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    fun getCurrentDate(): String {
        return formatDate(Date())
    }
}
