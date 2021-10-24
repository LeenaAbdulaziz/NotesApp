package com.example.notesapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    fun updatedrecycle(list:ArrayList<Note>){

        recycle.adapter = RecycleView (this, list)
        recycle.layoutManager = LinearLayoutManager(this)


    }

    fun update(note: Note) {
        var db = DBHlpr(applicationContext)
        db.update(note)
        updatedrecycle(db.retrieve())
    }
    fun deleteitem(note: Note) {
        var db = DBHlpr(applicationContext)
        db.delete(note)
        updatedrecycle(db.retrieve())
        Toast.makeText(applicationContext,"Successfully deleted",Toast.LENGTH_SHORT).show()

    }

    fun showDialog(n:Note) {
        var at= AlertDialog.Builder(this)
        at.setTitle("Edit Note")
        val input = EditText(this)

        input.setText(note.text)
        at.setView(input)
        at.setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
             update(Note(n.id,input.text.toString()))
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

    fun confirm(note: Note){
        var at= AlertDialog.Builder(this)
        at.setTitle("delete Note")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
           deleteitem(note)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

    }

