package com.example.assignmentamity.ui

import com.example.assignmentamity.domain.ToDoItem

data class ToDoViewState(
    val isLoading: Boolean = false,
    val isError: Throwable? = null,
    val items: List<ToDoItem> = listOf()
)