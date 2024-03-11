package com.example.assignmentamity.data.network

import com.example.assignmentamity.data.ToDoEntity
import com.example.assignmentamity.data.ToDoService
import javax.inject.Inject

interface TodoNetworkDataSource {

    suspend fun fetchTodoLists(): List<ToDoEntity>
}

class TodoNetworkDataSourceImpl @Inject constructor(
    private val toDoService: ToDoService
) : TodoNetworkDataSource {

    override suspend fun fetchTodoLists(): List<ToDoEntity> {
        return toDoService.getToDoList()
    }

}