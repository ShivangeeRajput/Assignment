package com.example.expensetracker.ui.fragments.recordexpense

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import java.util.*

class RecordExpenseFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbExpense: RadioButton
    private lateinit var rbIncome: RadioButton
    private lateinit var btnPickDate: ImageButton
    private lateinit var tvSelectedDate: TextView
    private lateinit var etDescription: EditText
    private lateinit var etAmount: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_record_expense, container, false)

        toolbar = view.findViewById(R.id.app_bar)
        rbExpense = view.findViewById(R.id.rbExpense)
        rbIncome = view.findViewById(R.id.rbIncome)
        btnPickDate = view.findViewById(R.id.btnPickDate)
        tvSelectedDate = view.findViewById(R.id.tvSelectedDate)
        etDescription = view.findViewById(R.id.etDescription)
        etAmount = view.findViewById(R.id.etAmount)
        btnSave = view.findViewById(R.id.btnSave)
        setupToolbar()
        setupDatePicker()

        btnSave.setOnClickListener {
            saveTransaction()
        }

        return view
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
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
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    tvSelectedDate.text = formattedDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun saveTransaction() {
        val type = if (rbExpense.isChecked) "Expense" else "Income"
        val description = etDescription.text.toString()
        val amount = etAmount.text.toString()
        val date = tvSelectedDate.text.toString()

        if (description.isBlank() || amount.isBlank() || date == "Select a date") {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(requireContext(), "Transaction Saved: $type, $description, $amount on $date", Toast.LENGTH_SHORT).show()
    }
}
