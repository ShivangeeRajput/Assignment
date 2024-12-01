package com.example.expensetracker.data.remote

import com.example.expensetracker.data.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object TransactionRepository {


    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())


    val transactions: Flow<List<Transaction>> = _transactions.asStateFlow()


    fun getTransactions(): Flow<List<Transaction>> {
        return transactions
    }


    suspend fun addTransaction(transaction: Transaction) {
        _transactions.value = _transactions.value.toMutableList().apply { add(transaction) }
    }


    suspend fun deleteTransaction(transactionId: String) {
        _transactions.value = _transactions.value.filterNot { it.id == transactionId }
    }


    suspend fun updateTransaction(updatedTransaction: Transaction) {
        _transactions.value = _transactions.value.map {
            if (it.id == updatedTransaction.id) updatedTransaction else it
        }
    }
}
