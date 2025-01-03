package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class UpdateCard : AppCompatActivity() {
    private lateinit var updateTitle: EditText
    private lateinit var updatePriority: EditText
    private lateinit var selectedDate: TextView
    private lateinit var selectedTime: TextView
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var selectDateButton: Button
    private lateinit var selectTimeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_card)

        // Initialize views
        updateTitle = findViewById(R.id.update_title)
        updatePriority = findViewById(R.id.update_priority)
        selectedDate = findViewById(R.id.selected_date)
        selectedTime = findViewById(R.id.selected_time)
        deleteButton = findViewById(R.id.delete_button)
        updateButton = findViewById(R.id.update_button)
        selectDateButton = findViewById(R.id.select_date_button)
        selectTimeButton = findViewById(R.id.select_time_button)

        // Handle window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val card = DataObject.getData(pos)
            updateTitle.setText(card.title)
            updatePriority.setText(card.priority)
            selectedDate.text = card.Rdate
            selectedTime.text = card.Rtime

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    updateTitle.text.toString(),
                    updatePriority.text.toString(),
                    selectedDate.text.toString(),
                    selectedTime.text.toString()
                )
                myIntent()
            }
        }

        selectDateButton.setOnClickListener { showDatePicker() }
        selectTimeButton.setOnClickListener { showTimePicker() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedTime.text = String.format("%02d:%02d", hourOfDay, minute)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
