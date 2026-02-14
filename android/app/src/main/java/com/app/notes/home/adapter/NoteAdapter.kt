package com.app.notes.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.notes.databinding.ItemNoteBinding
import com.app.notes.model.Note

class NoteAdapter(
    private val notes: List<Note>?
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes?.size ?: 0
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes?.get(position)
        holder.binding.tvTitle.text = note?.title
        holder.binding.tvDescription.text = note?.description
    }
}
