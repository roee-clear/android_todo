package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.data.Note
import com.example.todoapp.ui.components.NoteItem
import com.example.todoapp.ui.theme.TODOAppTheme

@Composable
fun NotesScreen(
    navigateToNoteFormScreen: () -> Unit,
    noteList: List<Note>,
    selectNote: (note: Note?) -> Unit,
    deleteNote: (note: Note) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier
            .padding(10.dp)
    ) {
        FloatingActionButton(
            onClick = {
                selectNote(null)

                navigateToNoteFormScreen()
            },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = stringResource(R.string.add_notes_button_label)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(noteList) { note ->
                NoteItem(
                    note = note,
                    onEditPress = {
                        selectNote(note)

                        navigateToNoteFormScreen()
                    },
                    onDeletePress = { deleteNote(note) },
                    modifier = Modifier
                )
            }
        }

        if (noteList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_notes_label),
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun PreviewNotesScreen(viewModel: TodoViewModel = viewModel()) {
    TODOAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NotesScreen(
                {}, listOf(),
                { viewModel.selectNote(it) },
                { viewModel.deleteNote(it) }, Modifier
            )
        }
    }
}
