package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context):SQLiteOpenHelper(context,"details",null,1) {
    var database:SQLiteDatabase=writableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL("Create table Notes(id INTEGER PRIMARY KEY,Note text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun save(s:String): Long {
        var content=ContentValues()
        content.put("Note",s)
       var status= database.insert("Notes",null,content)
        return status
    }

    fun retrieve():ArrayList<Note> {
       var noteList= arrayListOf<Note>()
        var c:Cursor=database.query("Notes",null,null,
            null,null,null,null)

        c.moveToFirst()
        while( !c.isAfterLast){
             val id =c.getInt(c.getColumnIndex("id"))
             val note =c.getString(c.getColumnIndex("Note"))
            noteList.add(Note(id,note))
            c.moveToNext()
        }
        return noteList
    }

    fun delete(note: Note) {
        database.delete("Notes","id=${note.id}", null)

    }

    fun update(note: Note){
        val content=ContentValues()
        content.put("id",note.id)
        content.put("note",note.note)
        database.update("Notes",content,"id=${note.id}", null)

    }
}