package com.example.expensetracker.data

data class Transaction(
    val id: String = "",
    val date: String = "",
    val type: String = "",
    val amount: Double = 0.0,
    val description: String = ""
)