package com.example.todoapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Note, onEditPress: () -> Unit, onDeletePress: () -> Unit, modifier: Modifier) {
    Card(
        onClick = onEditPress,
        modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            text = note.title,
            modifier.padding(10.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        Text(text = note.description, modifier.padding(10.dp))

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = onEditPress) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.note_edit_button)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            IconButton(onClick = onDeletePress) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.note_delete_button)
                )
            }
        }
    }
}
