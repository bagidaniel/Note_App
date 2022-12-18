package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentAddBinding
import com.example.notes.model.Note
import com.example.notes.viewmodel.NoteViewModel

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        binding.addButton.setOnClickListener{
            val title = binding.addTitle.text.toString()
            val description = binding.addDescription.text.toString()
            val note = Note(0, title, description)
            noteViewModel.insertNote(note)
            view.findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }
}