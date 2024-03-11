package com.example.assignmentamity.data.network

import com.example.assignmentamity.data.ToDoResponse
import com.example.assignmentamity.data.ToDoService
import javax.inject.Inject

interface TodoNetworkDataSource {

    suspend fun fetchTodoLists(): List<ToDoResponse>
}

class TodoNetworkDataSourceImpl @Inject constructor(
    private val toDoService: ToDoService
) : TodoNetworkDataSource {

    override suspend fun fetchTodoLists(): List<ToDoResponse> {
        return toDoService.getToDoList()
    }

}