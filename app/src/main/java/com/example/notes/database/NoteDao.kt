package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from noteTable")
    fun getAllNotes(): LiveData<List<Note>>
}