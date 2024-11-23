package com.example.expensetracker.data.remote

import com.example.expensetracker.data.Transaction
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val db: DatabaseReference
) {
    suspend fun addTransaction(transaction: Transaction) {
        val transactionId = db.push().key ?: return
        db.child("transactions").child(transactionId).setValue(transaction)
    }

    fun getTransactions() = db.child("transactions").get().await()
}