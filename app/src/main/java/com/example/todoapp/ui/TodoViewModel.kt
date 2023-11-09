package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Note
import com.example.todoapp.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(notes = initNotesState()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun addNote(note: Note) {
        _uiState.update { currentState ->
            currentState.copy(
                notes = uiState.value.notes.plus(note)
            )
        }
    }

    fun updateNote(note: Note) {
        val noteIndex = uiState.value.notes.indexOf(uiState.value.selectedNote)
        var notesArr = uiState.value.notes.toMutableList()
        notesArr[noteIndex] = note

        _uiState.update { currentState ->
            currentState.copy(
                notes = notesArr
            )
        }
    }

    fun selectNote(note: Note? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedNote = note
            )
        }
    }

    fun deleteNote(note: Note) {
        val noteIndex = uiState.value.notes.indexOf(note)
        var notesArr = uiState.value.notes.toMutableList()
        notesArr.removeAt(noteIndex)

        _uiState.update { currentState ->
            currentState.copy(
                notes = notesArr
            )
        }
    }

    private fun initNotesState(): List<Note> {
        return mutableListOf()
    }
}


