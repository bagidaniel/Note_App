package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.example.notes.databinding.NoteLayoutBinding
import com.example.notes.model.Note
import com.example.notes.ui.ListFragmentDirections


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.description == newItem.description &&
                        oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.itemTitle.text = currentNote.title
        holder.itemBinding.itemDescription.text = currentNote.description

        holder.itemView.setOnClickListener { view ->
            val direction = ListFragmentDirections.actionListFragmentToEditFragment(currentNote)
            view.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}