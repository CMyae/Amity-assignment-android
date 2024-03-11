package com.example.assignmentamity.data.local

import com.example.assignmentamity.data.ToDoResponse
import com.example.assignmentamity.domain.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TodoLocalDataSource {

    suspend fun insertAllTodo(items: List<ToDoResponse>)

    fun getTodoList(): Flow<List<ToDoItem>>
}

class TodoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : TodoLocalDataSource {

    override suspend fun insertAllTodo(items: List<ToDoResponse>) {
        toDoDao.insertAll(items)
    }

    override fun getTodoList(): Flow<List<ToDoItem>> {
        return toDoDao.getTodoList().map { list ->
            list.map {
                ToDoItem(
                    userId = it.userId,
                    id = it.id,
                    title = it.title.orEmpty(),
                    completed = it.completed
                )
            }
        }
    }

}