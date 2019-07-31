package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnTambah.setOnClickListener{
            val intent = Intent(this, Tambah::class.java)
            startActivity(intent)
        }

        btnList.setOnClickListener{
            val intent = Intent(this, List::class.java)
            startActivity(intent)
        }
    }
}
