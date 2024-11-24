package com.example.expensetracker.ui.fragments.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.adapter.TransactionAdapter
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.data.remote.TransactionRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.filter

class DashboardFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var transactionsRecyclerView: RecyclerView
    private lateinit var fabAddExpense: FloatingActionButton
    private val transactionsAdapter = TransactionAdapter(::onTransactionClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        searchEditText = view.findViewById(R.id.etSearch)
        transactionsRecyclerView = view.findViewById(R.id.rvTransactions)
        fabAddExpense = view.findViewById(R.id.fabAddExpense)

        transactionsRecyclerView.adapter = transactionsAdapter
        transactionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_recordExpenseFragment)
        }

        setupSearch()
        loadTransactions()

        return view
    }

    private fun loadTransactions() {
        val transactions = TransactionRepository.getTransactions()
        transactionsAdapter.submitList(transactions)
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                val filteredList = TransactionRepository.getTransactions().filter {
                    it.description.contains(query, ignoreCase = true)
                }
                transactionsAdapter.submitList(filteredList)
            }
        })
    }

    private fun onTransactionClicked(transaction: Transaction) {
        val action = DashboardFragmentDirections.actionDashboardFragmentToTransactionDetailsFragment(
            type = transaction.type,
            date = transaction.date,
            description = transaction.description,
            amount = transaction.amount.toString()
        )
        findNavController().navigate(action)
    }
}
