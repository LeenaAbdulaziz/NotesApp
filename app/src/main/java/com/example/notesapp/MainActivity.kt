package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var save:Button
    lateinit var note:EditText
    lateinit var recycle:RecyclerView
   lateinit var noteList:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        note=findViewById(R.id.ednote)
        save=findViewById(R.id.btnsave)
        recycle=findViewById(R.id.rv)
        noteList= arrayListOf()
        recycle.adapter = RecycleView (this, noteList)
        recycle.layoutManager = LinearLayoutManager(this)
        save.setOnClickListener {
            var s=note.text.toString()
           var db=DBHlpr(applicationContext)
            var sataus=db.save(s)
            Toast.makeText(applicationContext,"Successfully added"+sataus, Toast.LENGTH_SHORT).show()
            recycle.adapter?.notifyDataSetChanged()
            note.text.clear()

        }
    }
}