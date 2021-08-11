package com.example.emoji_status

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import java.util.zip.Inflater

class todoadapter (private val todos:MutableList<todo>):RecyclerView.Adapter<todoadapter.TodoViewHolder>()
{
    class TodoViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        )
    }

    fun addtodo(note: todo){
        todos.add(note)
        notifyItemInserted(todos.size-1)
    }

    fun deletetodo(){
        todos.removeAll { todo->
            todo.checked
        }
        notifyDataSetChanged()
    }

    private fun togglestrikethrough(note:TextView,checked:Boolean){
        if(checked){
            note.paintFlags=note.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            note.paintFlags=note.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currenttodo=todos[position]
        holder.itemView.apply {
            note.text=currenttodo.note
            check.isChecked=currenttodo.checked
            togglestrikethrough(note,currenttodo.checked)
            check.setOnCheckedChangeListener { _, isChecked ->
                togglestrikethrough(note,isChecked)
                currenttodo.checked=!currenttodo.checked

            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}