package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class List<T> : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<m_barang>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        ref = FirebaseDatabase.getInstance().getReference("m_barang")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val barang = h.getValue(m_barang::class.java)
                        list.add(barang!!)
                    }
                    val adapter = Adapter(this@List,R.layout.barang,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
