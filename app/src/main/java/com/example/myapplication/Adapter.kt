package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.FirebaseDatabase

class Adapter(private val mCtx: Context, private val layoutResId: Int, private val list: MutableList<m_barang>)
    : ArrayAdapter<m_barang>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textTanggal = view.findViewById<TextView>(R.id.textTanggal)
        val txtUpdate = view.findViewById<TextView>(R.id.textUpdate)
        val Delete = view.findViewById<TextView>(R.id.textDelete)

        val barang = list[position]

        textNama.text = barang.nama_barang
        textTanggal.text = barang.tanggal


        txtUpdate.setOnClickListener {
            update_barang(barang)
        }
        Delete.setOnClickListener {
//            Deleteinfo(barang)
        }

        return view

    }


    private fun update_barang(barang: m_barang) {

        openActivity(Update::class.java) {
            putString("string.key", "string.value")
        }

    }
}