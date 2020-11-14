package com.sideki.simplelist.views.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sideki.simplelist.R
import com.sideki.simplelist.model.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class ListRecycler(private val context: Context) : RecyclerView.Adapter<ListRecycler.ViewHolder>() {

    private var listNote = emptyList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note, parent, false
            )
        )

    override fun getItemCount() = listNote.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = listNote[position]

        holder.itemView.imageView.setImageBitmap(curItem.image)
        holder.itemView.tv_title.text = curItem.title
        holder.itemView.tv_description.text = curItem.description
        holder.itemView.tv_preority.text = curItem.priority.toString()

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(curItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setNotes(note: List<Note>) {
        this.listNote = note
        notifyDataSetChanged()
    }

    fun getNote(position: Int): Note {
        return listNote.get(position)
    }
}