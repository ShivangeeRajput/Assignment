package com.example.expensetracker.ui.fragments.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expensetracker.R

class TransactionDetailsFragment : Fragment() {

    private val args: TransactionDetailsFragmentArgs by navArgs()

    private lateinit var tvTransactionType: TextView
    private lateinit var tvTransactionDate: TextView
    private lateinit var tvTransactionAmount: TextView
    private lateinit var tvTransactionDescription: TextView
    private lateinit var btnEdit: ImageButton
    private lateinit var btnDelete: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_details, container, false)

        // Initialize views
        tvTransactionType = view.findViewById(R.id.tvTransactionType)
        tvTransactionDate = view.findViewById(R.id.tvTransactionDate)
        tvTransactionAmount = view.findViewById(R.id.tvTransactionAmount)
        tvTransactionDescription = view.findViewById(R.id.tvTransactionDescription)
        btnEdit = view.findViewById(R.id.btnEdit)
        btnDelete = view.findViewById(R.id.btnDelete)


        setupData()
        setupActions()

        return view
    }

    private fun setupData() {

        tvTransactionType.text = args.type
        tvTransactionDate.text = args.date
        tvTransactionAmount.text = "₹${args.amount}"
        tvTransactionDescription.text = args.description ?: "No Description"
    }

    private fun setupActions() {
        btnEdit.setOnClickListener {
            val action = TransactionDetailsFragmentDirections
                .actionTransactionDetailsFragmentToRecordExpenseFragment(
                    type = args.type,
                    date = args.date,
                    description = args.description,
                    amount = args.amount
                )
            findNavController().navigate(action)
        }

        btnDelete.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
