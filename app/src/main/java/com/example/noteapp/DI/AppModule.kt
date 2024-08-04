package com.example.noteapp.DI

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDatabaseDao
import com.example.noteapp.data.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDoa(noteDatabaseDao: NotesDataBase):NoteDatabaseDao = noteDatabaseDao.noteDAO()

    @Singleton
    @Provides
    fun ProvideAppDatabase(@ApplicationContext context: Context): NotesDataBase
    = Room.databaseBuilder(
        context,
        NotesDataBase::class.java,
        "notes_db"
    ).fallbackToDestructiveMigrationFrom().build()

}