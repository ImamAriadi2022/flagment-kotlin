package com.kimora.app.screens.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.kimora.app.data.local.NoteDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(navController: NavController, noteId: Int) {
    val context = LocalContext.current
    val db = remember { NoteDatabase.getDatabase(context) }
    val note = remember { mutableStateOf<com.kimora.app.data.local.Note?>(null) }

    LaunchedEffect(noteId) {
        // note.value = db.noteDao().getNoteById(noteId) //This function doesn't exist yet
    }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    note.value?.let {
        title = it.title
        description = it.description
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Note") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }
            )
            Button(onClick = {
                scope.launch {
                    val updatedNote = com.kimora.app.data.local.Note(
                        id = noteId,
                        title = title,
                        description = description,
                        timestamp = System.currentTimeMillis()
                    )
                    db.noteDao().updateNote(updatedNote)
                    navController.popBackStack()
                }
            }) {
                Text("Update Note")
            }
        }
    }
}