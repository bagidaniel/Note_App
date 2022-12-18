package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.Note
import com.example.notes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application,
    private val noteRepository: NoteRepository
) : AndroidViewModel(app) {

    fun getAllNote() = noteRepository.getAllNotes()


    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }
}