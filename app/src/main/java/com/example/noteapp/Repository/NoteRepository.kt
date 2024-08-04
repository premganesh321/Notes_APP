package com.example.noteapp.Repository

import com.example.noteapp.data.NoteDatabaseDao
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

//Used dependency-injection
class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    //crud operations
    suspend fun addNote(note : Note) = noteDatabaseDao.insert(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.Update(note)
    suspend fun deleteNotebyId(id : UUID) = noteDatabaseDao.deleteNotebyId(id)
    suspend fun deleteallnotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
}