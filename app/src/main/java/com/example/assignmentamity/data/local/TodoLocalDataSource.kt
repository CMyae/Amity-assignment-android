package com.example.assignmentamity.data.local

import com.example.assignmentamity.data.ToDoEntity
import com.example.assignmentamity.domain.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TodoLocalDataSource {

    suspend fun insertAllTodo(items: List<ToDoEntity>)

    fun getTodoList(): Flow<List<ToDoItem>>
}

class TodoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : TodoLocalDataSource {

    override suspend fun insertAllTodo(items: List<ToDoEntity>) {
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