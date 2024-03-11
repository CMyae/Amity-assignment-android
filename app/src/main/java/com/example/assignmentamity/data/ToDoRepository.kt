package com.example.assignmentamity.data

import com.example.assignmentamity.data.local.TodoLocalDataSource
import com.example.assignmentamity.data.network.TodoNetworkDataSource
import com.example.assignmentamity.domain.ToDoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ToDoRepository {

    suspend fun fetchToDoList()

    fun getToDoList(): Flow<List<ToDoItem>>
}

class TodoRepositoryImpl @Inject constructor(
    private val networkDataSource: TodoNetworkDataSource,
    private val localDataSource: TodoLocalDataSource
) : ToDoRepository {

    override suspend fun fetchToDoList() {
        val todoList = networkDataSource.fetchTodoLists()
        localDataSource.insertAllTodo(todoList)
    }

    override fun getToDoList(): Flow<List<ToDoItem>> {
        return localDataSource.getTodoList()
    }

}