package com.example.expensetracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.data.remote.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    val transactions = MutableLiveData<List<Transaction>>()

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }

    fun fetchTransactions() {
        viewModelScope.launch {
            val data = repository.getTransactions()
            transactions.postValue(data)
        }
    }
}
