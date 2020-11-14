package com.sideki.simplelist.model.repository

import androidx.lifecycle.LiveData
import com.sideki.simplelist.model.data.NoteDao
import com.sideki.simplelist.model.entity.Note

class Repository(private var noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}