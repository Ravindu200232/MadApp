package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

class CreateCard : ComponentActivity() {
    private lateinit var selectedDateTextView: TextView
    private lateinit var selectedTimeTextView: TextView
    private lateinit var selectedEndDateTextView: TextView
    private lateinit var selectedEndTimeTextView: TextView
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        // Initialize TextViews and EditTexts
        selectedDateTextView = findViewById(R.id.selected_date)
        selectedTimeTextView = findViewById(R.id.selected_time)
        selectedEndDateTextView = findViewById(R.id.selected_end_date)
        selectedEndTimeTextView = findViewById(R.id.selected_end_time)
        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)

        // Initialize Buttons
        val selectDateButton: Button = findViewById(R.id.select_date_button)
        val selectTimeButton: Button = findViewById(R.id.select_time_button)
        val selectEndDateButton: Button = findViewById(R.id.select_end_date_button)
        val selectEndTimeButton: Button = findViewById(R.id.select_end_time_button)
        val saveButton: Button = findViewById(R.id.save_button)

        // Set up date picker button click listener
        selectDateButton.setOnClickListener {
            showDatePicker { date ->
                selectedDateTextView.text = dateFormat.format(date)
            }
        }

        // Set up time picker button click listener
        selectTimeButton.setOnClickListener {
            showTimePicker { time ->
                selectedTimeTextView.text = timeFormat.format(time)
            }
        }

        // Set up end date picker button click listener
        selectEndDateButton.setOnClickListener {
            showDatePicker { date ->
                selectedEndDateTextView.text = dateFormat.format(date)
            }
        }

        // Set up end time picker button click listener
        selectEndTimeButton.setOnClickListener {
            showTimePicker { time ->
                selectedEndTimeTextView.text = timeFormat.format(time)
            }
        }

        // Set up save button click listener
        saveButton.setOnClickListener {
            val title = createTitle.text.toString().trim()
            val priority = createPriority.text.toString().trim()
            val date = selectedDateTextView.text.toString().trim()
            val time = selectedTimeTextView.text.toString().trim()
            val endDate = selectedEndDateTextView.text.toString().trim()
            val endTime = selectedEndTimeTextView.text.toString().trim()


            if (title.isNotEmpty() && priority.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && endDate.isNotEmpty() && endTime.isNotEmpty()) {
                DataObject.setData(title, priority, date, time, endDate, endTime)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Optional: to close the current activity after saving
            }
        }
    }

    private fun showDatePicker(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, dayOfMonth ->
                val date = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, dayOfMonth)
                }.time
                onDateSelected(date)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker(onTimeSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val time = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }.time
                onTimeSelected(time)
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }
}
