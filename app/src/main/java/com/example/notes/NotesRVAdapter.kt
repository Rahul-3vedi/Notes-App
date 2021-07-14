package com.example.notes

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,val listener:INotesRVAdapter):RecyclerView.Adapter<NotesRVAdapter.NoteViewholder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        val viewHolder = NoteViewholder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener(){
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}
interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}