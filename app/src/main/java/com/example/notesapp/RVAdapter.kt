package com.example.notesapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*


//import kotlinx.android.synthetic.main.single_item.view.*

class RecycleView(val activity: MainActivity, val notes: ArrayList<Note>) : RecyclerView.Adapter<RecycleView.recyclerViewHolder>() {
    class recyclerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        return recyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {
        val note = notes[position]

        holder.itemView.apply {
            if (position % 2 == 0) {
                lll.setBackgroundColor(Color.GRAY)
                textview2.setTextColor(Color.WHITE)
            } else {
                lll.setBackgroundColor(Color.WHITE)
                textview2.setTextColor(Color.BLACK)
            }
            textview2.text = note.note

            imageView.setOnClickListener {
                activity.showDialog(note)
            }
            imageView2.setOnClickListener {
                activity.confirm(note)
            }
        }}


    override fun getItemCount()=notes.size
}