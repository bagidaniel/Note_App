package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.adapter.NoteAdapter
import com.example.notes.databinding.FragmentListBinding
import com.example.notes.model.Note
import com.example.notes.viewmodel.NoteViewModel

class ListFragment : Fragment(){

    private lateinit var binding: FragmentListBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()
        binding.newButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getAllNote().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
            }
        }
    }
}