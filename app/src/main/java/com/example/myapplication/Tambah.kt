package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tambah.*
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.text.InputType
import java.util.*
import android.view.View.OnFocusChangeListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Tambah : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        inputTanggal.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                inputTanggal.setShowSoftInputOnFocus(false);
                inputTanggal.setInputType(InputType.TYPE_NULL);
                inputTanggal.setFocusable(false);

                if (MotionEvent.ACTION_UP == event.action){
                    val newCalendar = Calendar.getInstance()

                    val datePickerDialog = DatePickerDialog(this@Tambah,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            val newDate = Calendar.getInstance()
                            newDate.set(year, monthOfYear, dayOfMonth)
                            val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                            inputTanggal.setText(dateFormatter.format(newDate.getTime()))
                        },
                        newCalendar.get(Calendar.YEAR),
                        newCalendar.get(Calendar.MONTH),
                        newCalendar.get(Calendar.DAY_OF_MONTH)
                    )

                    datePickerDialog.show()
                }
                return false
            }
        })

        ref = FirebaseDatabase.getInstance().getReference("m_barang")

        fun saveData() {
            val nama_barang = inputNama.text.toString()
            val tanggal = inputTanggal.text.toString()

            val userId = ref.push().key.toString()
            val barang = m_barang(userId,nama_barang,tanggal)

            ref.child(userId).setValue(barang).addOnCompleteListener {
                Toast.makeText(this, "Sukses",Toast.LENGTH_SHORT).show()
                inputNama.setText("")
                inputTanggal.setText("")
                val intent = Intent(this, List::class.java)
                startActivity(intent)
            }
        }

        btnSave.setOnClickListener{
            saveData()
        }




    }
}
