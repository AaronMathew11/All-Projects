package com.example.emoji_status

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class fireadapter( private val notelist:ArrayList<fire>): RecyclerView.Adapter<fireadapter.Myviewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return Myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {

        val currentitem= notelist[position]

        holder.itemView.apply {
            note.text=currentitem.note
        }

    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    class Myviewholder(itemView: View):RecyclerView.ViewHolder(itemView)


}