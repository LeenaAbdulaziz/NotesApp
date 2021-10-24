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
            p0.execSQL("Create table Notes(Note text)")
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

    fun retrieve():ArrayList<String> {
       var noteList= arrayListOf<String>()
        var c:Cursor=database.query("Notes",null,null,
            null,null,null,null)

        c.moveToFirst()
        while( !c.isAfterLast){
             var note =c.getString(c.getColumnIndex("Note"))
            noteList.add(note)
            c.moveToNext()
        }
        return noteList
    }
}