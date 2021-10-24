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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db = DBHlpr(applicationContext)
        note=findViewById(R.id.ednote)
        save=findViewById(R.id.btnsave)
        recycle=findViewById(R.id.rv)
        updatedrecycle(db.retrieve())

        save.setOnClickListener {
            var s=note.text.toString()
            if(s.isNotEmpty()) {
                var sataus = db.save(s)
                Toast.makeText(applicationContext, "Successfully added" + sataus, Toast.LENGTH_SHORT).show()
                note.text.clear()
                updatedrecycle(db.retrieve())
            }
            else{
                Toast.makeText(applicationContext,"please add note first",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun updatedrecycle(list:ArrayList<String>){

        recycle.adapter = RecycleView (this, list)
        recycle.layoutManager = LinearLayoutManager(this)


    }
}