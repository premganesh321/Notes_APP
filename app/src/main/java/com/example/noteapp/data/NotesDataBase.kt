package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.model.Note

@Database(entities = [Note::class], version = 1 , exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {
     abstract fun noteDAO() : NoteDatabaseDao
}