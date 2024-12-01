package com.example.expensetracker.ui.fragments.transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import java.util.*

class RecordExpenseFragment : Fragment() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var etDescription: EditText
    private lateinit var etAmount: EditText
    private lateinit var btnPickDate: Button
    private lateinit var rbExpense: RadioButton
    private lateinit var rbIncome: RadioButton
    private lateinit var btnSave: Button

    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_record_expense, container, false)
        tvSelectedDate = view.findViewById(R.id.tvSelectedDate)
        etDescription = view.findViewById(R.id.etDescription)
        etAmount = view.findViewById(R.id.etAmount)
        btnPickDate = view.findViewById(R.id.btnPickDate)
        rbExpense = view.findViewById(R.id.rbExpense)
        rbIncome = view.findViewById(R.id.rbIncome)
        btnSave = view.findViewById(R.id.btnSave)

        tvSelectedDate.text = "Select a date"

        setupDatePicker()
        setupSaveButton()

        return view
    }
    private fun setupDatePicker() {
        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear"
                    tvSelectedDate.text = selectedDate
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
    }

    private fun setupSaveButton() {
        btnSave.setOnClickListener {
            val description = etDescription.text.toString()
            val amount = etAmount.text.toString().toDoubleOrNull()

            if (description.isNotEmpty() && amount != null && selectedDate.isNotEmpty()) {
                val type = if (rbExpense.isChecked) "Expense" else "Income"
                saveExpenseData(type, selectedDate, description, amount)
            } else {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveExpenseData(type: String, date: String, description: String, amount: Double) {

        Toast.makeText(requireContext(), "Saved $type: $description, Amount: ₹$amount on $date", Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }
}
