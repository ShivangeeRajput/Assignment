package com.example.expensetracker.data

data class Transaction(
    val id: String,
    val type: String,
    val amount: Float,
    val date: String,
    val description: String
)