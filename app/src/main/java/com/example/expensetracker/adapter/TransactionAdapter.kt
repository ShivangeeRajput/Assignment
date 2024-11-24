package com.example.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter(
    private val onClick: (Transaction) -> Unit
) : ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {

           val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
            return TransactionViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.date == newItem.date && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                  return oldItem == newItem
            }
        }
    }

    class TransactionViewHolder(
        itemView: View,
        private val onClick: (Transaction) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaction: Transaction) {
            itemView.apply {
                tvTransactionType.text = transaction.type
                tvTransactionAmount.text = "₹${transaction.amount}"
                tvTransactionDate.text = transaction.date
                tvTransactionDescription.text = transaction.description
                setOnClickListener { onClick(transaction) }
            }
        }
    }
}
