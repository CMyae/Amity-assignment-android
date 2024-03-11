package com.example.assignmentamity.domain

data class ToDoItem(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)