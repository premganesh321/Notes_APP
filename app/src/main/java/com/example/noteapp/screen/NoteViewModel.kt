package com.example.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Repository.NoteRepository
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

//class NoteViewModel : ViewModel(){
//    private var listofallnotes = mutableStateListOf<Note>()
//
//    init {
//        listofallnotes.addAll(NotesDataSource().loadNotes())
//    }
//    fun addNote(note : Note){
//        listofallnotes.add(note);
//    }
//    fun removeNote(note : Note){
//        listofallnotes.remove(note);
//    }
//    fun getallNotes() : List<Note>{
//        return listofallnotes
//    }
//}


@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel(){
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{
                if(it.isNullOrEmpty()){

                }
                else{
                    _noteList.value = it
                }
            }
        }
    }
    fun addNote(note : Note){
        viewModelScope.launch {
            repository.addNote(note)
        }
    }
    fun UpdateNote(note : Note) = viewModelScope.launch { repository.updateNote(note) }
    fun deleteNote(id : UUID) = viewModelScope.launch {
        repository.deleteNotebyId(id)
        _noteList.value = _noteList.value.filter { it.id != id }
    }
}