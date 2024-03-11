package com.example.assignmentamity.domain

import com.example.assignmentamity.data.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val repo: ToDoRepository
) {
    fun execute(): Flow<List<ToDoItem>> {
        return repo.getToDoList()
    }
}