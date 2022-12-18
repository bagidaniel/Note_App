package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.model.Note
import com.example.notes.viewmodel.NoteViewModel

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val args: EditFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.editTitle.setText(currentNote.title)
        binding.editDescription.setText(currentNote.description)

        binding.updateButton.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            val note = Note(currentNote.id, title, description)
            noteViewModel.updateNote(note)
            view.findNavController().navigate(R.id.action_editFragment_to_listFragment)
        }

        binding.deleteButton.setOnClickListener {
            noteViewModel.deleteNote(currentNote)
            view.findNavController().navigate(R.id.action_editFragment_to_listFragment)
        }
    }
}