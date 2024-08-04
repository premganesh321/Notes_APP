package com.example.noteapp.model
import android.icu.text.DateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

fun getCurrentDateAndTime(): Pair<String, String> {
    val calendar = Calendar.getInstance()
    val dateformat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).format(calendar.time)
    val timeformat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(calendar.time)
    return Pair(dateformat, timeformat)
}

@Entity(tableName = "Note_Table")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "Note_Title")
    val title: String,
    @ColumnInfo(name = "Note_Description")
    val description: String,
    @ColumnInfo(name = "Note_Entry_Date")
    val time: String = getCurrentDateAndTime().second,
    @ColumnInfo(name = "Note_Entry_Time")
    val dateFormat: String = getCurrentDateAndTime().first
)