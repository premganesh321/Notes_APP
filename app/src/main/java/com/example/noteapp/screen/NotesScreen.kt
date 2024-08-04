package com.example.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.R
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.widgets.InputTextFieldofNoteScreen
import com.example.noteapp.widgets.SaveButton
import java.util.UUID
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(modifier: Modifier = Modifier , notes : List<Note> = NotesDataSource().loadNotes() , onAddNote : (Note)->Unit , onRemoveNote : (UUID)->Unit , onUpdateNote: (Note) -> Unit) {
    val title = remember {
        mutableStateOf("")
    }
    val descrption = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val noteToUpdate = remember {
        mutableStateOf<Note?>(null)
    }
    LaunchedEffect(noteToUpdate.value) {
        noteToUpdate.value?.let { note ->
            title.value = note.title
            descrption.value = note.description
        }
    }
     Column(
         modifier = Modifier
     ){
         TopAppBar(title = {
             Text(text = stringResource(id = R.string.app_name) , color = Color.Black)
         },
             actions = {
                 IconButton(onClick = {}) {
                     Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                 }
             },
             colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFeedd82))
         )
         Column(
             horizontalAlignment = Alignment.CenterHorizontally,
             modifier = Modifier.fillMaxWidth().background(color = Color(0xFFE8F5E9))
         ){
             InputTextFieldofNoteScreen(modifier = Modifier.padding(top = 7.dp , bottom = 9.dp),inputText = title.value, label = "Title",
                 onTextChange = {
                     if(it.all { char->
                         char.isLetter() || char.isWhitespace() || char.isDigit()
                     }){
                         title.value = it
                     }
                 }
             )
             InputTextFieldofNoteScreen(modifier = Modifier.padding(top = 7.dp , bottom = 20.dp),inputText = descrption.value, label = "Add a Note", maxLine = 10,
                 onTextChange = {
                     if(it.all { char->
                         char.isLetter() || char.isWhitespace() || char.isDigit()
                     }){
                         descrption.value = it
                     }
                 }
             )
             SaveButton(text = "Save",
                 onclick = {
                     if (title.value.trim().isNotEmpty() && descrption.value.trim().isNotEmpty()) {
                         if (noteToUpdate.value != null) {
                             onUpdateNote(
                                 noteToUpdate.value!!.copy(title = title.value, description = descrption.value)
                             )
                             noteToUpdate.value = null
                             Toast.makeText(context, "Note Updated :)", Toast.LENGTH_SHORT).show()
                         }else {
                             onAddNote(
                                 Note(
                                     title = title.value,
                                     description = descrption.value
                                 )
                             )
                             Toast.makeText(context, "Note Added :)", Toast.LENGTH_SHORT).show()
                         }
                         title.value = ""
                         descrption.value = ""
                     }
                 }
             )
         }
         Divider(modifier = Modifier.padding(10.dp))
         LazyColumn {
             items(notes) { note ->
                 NoteRow(
                     note = note,
                     onNoteClicked = { onRemoveNote(it) },
                     onNoteLongPressed = { longPressedNote ->
                         title.value = longPressedNote.title
                         descrption.value = longPressedNote.description
                         noteToUpdate.value = longPressedNote
                     }
                 )
             }
         }
     }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteRow(modifier: Modifier = Modifier , note : Note , onNoteClicked : (UUID)->Unit , onNoteLongPressed : (Note)->Unit) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 30.dp))
            .fillMaxWidth(),
        color = Color(0xFFF1B075)
    ){
        Column(
            modifier = Modifier
                .combinedClickable(
                    onClick = { onNoteClicked(note.id) },
                    onLongClick = { onNoteLongPressed(note) }
                )
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = note.time, style = MaterialTheme.typography.labelSmall)
            Text(text = note.dateFormat, style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFF3A58EE))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteScreenPrev() {
    NoteScreen(notes = NotesDataSource().loadNotes() , onAddNote = {} , onRemoveNote = {}, onUpdateNote = {})
}