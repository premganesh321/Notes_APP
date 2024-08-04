package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM Note_Table")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note_Table where id=:id")
    suspend fun getNoteById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Update(note : Note)

    @Query("DELETE FROM Note_Table")
    suspend fun deleteAll()

    @Query("DELETE FROM Note_Table WHERE id = :id")
    suspend fun deleteNotebyId(id: UUID): Int
}
