package com.example.assignmentamity.domain

import com.example.assignmentamity.data.ToDoRepository
import javax.inject.Inject

class RefreshTodoListUseCase @Inject constructor(
    private val repo: ToDoRepository
) {

    suspend fun execute() {
        return repo.fetchToDoList()
    }
}