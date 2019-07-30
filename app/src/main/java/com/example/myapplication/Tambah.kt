package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tambah.*
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.*


class Tambah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        btnCalendar.setOnClickListener{
            val newCalendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(this@Tambah,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                    inputTanggalLahir.setText(dateFormatter.format(newDate.getTime()))
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }
    }
}
