package com.example.todoapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.Note
import com.example.todoapp.ui.components.NoteField

@Composable
fun FormScreen(
    note: Note?,
    onSubmitPress: () -> Unit,
    updateNote: (note: Note) -> Unit,
    addNote: (note: Note) -> Unit,
    modifier: Modifier
) {
    var titleText by remember { mutableStateOf(note?.title ?: "") }
    var descriptionText by remember { mutableStateOf(note?.description ?: "") }

    Column(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.note_form_title_label))

        Spacer(modifier = Modifier.height(15.dp))

        NoteField(
            label = R.string.note_field_title_label,
            isSingleLine = true,
            value = titleText,
            onValueChanged = { titleText = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(15.dp))

        NoteField(
            label = R.string.note_field_description_label,
            isSingleLine = false,
            value = descriptionText,
            onValueChanged = { descriptionText = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    val noteDetails = (object : Note {
                        override val title = titleText
                        override val description = descriptionText
                    })
                    if (note != null) {
                        updateNote(noteDetails)
                    } else {
                        addNote(noteDetails)
                    }

                    onSubmitPress()
                },
                modifier.width(200.dp),
                enabled = titleText.isNotEmpty() && descriptionText.isNotEmpty(),
            ) {
                Text(text = stringResource(id = R.string.note_form_button_label))
            }
        }
    }
}