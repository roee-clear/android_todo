package com.example.todoapp.data

data class UiState(
    val notes: List<Note> = listOf(),
    val selectedNote: Note? = null
)


