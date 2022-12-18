package com.example.notes.repository

import com.example.notes.model.Note
import com.example.notes.database.NoteDatabase

class NoteRepository(private val noteDatabase: NoteDatabase) {

    suspend fun insert(note: Note) = noteDatabase.getNoteDao().insert(note)
    suspend fun delete(note: Note) = noteDatabase.getNoteDao().delete(note)
    suspend fun update(note: Note) = noteDatabase.getNoteDao().update(note)
    fun getAllNotes() = noteDatabase.getNoteDao().getAllNotes()
}