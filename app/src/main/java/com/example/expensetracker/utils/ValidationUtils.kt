package com.example.expensetracker.utils

object ValidationUtils {

    fun isValidAmount(amount: String): Boolean {
        return amount.isNotEmpty() && amount.toDoubleOrNull() != null && amount.toDouble() > 0
    }

    fun isValidDescription(description: String): Boolean {
        return description.isNotEmpty()
    }
}
