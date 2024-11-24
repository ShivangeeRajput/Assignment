package com.example.expensetracker.data.remote

import com.example.expensetracker.data.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object TransactionRepository {

    private val transactions = MutableStateFlow<List<Transaction>>(emptyList())

    fun getTransactions(): Flow<List<Transaction>> {
        return transactions
    }

    suspend fun addTransaction(transaction: Transaction) {
        val currentList = transactions.value.toMutableList()
        currentList.add(transaction)
        transactions.emit(currentList)
    }

    suspend fun deleteTransaction(transactionId: String) {
        val currentList = transactions.value.toMutableList()
        currentList.removeAll { it.id == transactionId }
        transactions.emit(currentList)
    }

    suspend fun updateTransaction(updatedTransaction: Transaction) {

        val currentList = transactions.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == updatedTransaction.id }
        if (index != -1) {
            currentList[index] = updatedTransaction
            transactions.emit(currentList)
        }
    }
}

