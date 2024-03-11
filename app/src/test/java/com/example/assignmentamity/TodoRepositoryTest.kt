package com.example.assignmentamity

import app.cash.turbine.test
import com.example.assignmentamity.data.ToDoEntity
import com.example.assignmentamity.data.TodoRepositoryImpl
import com.example.assignmentamity.data.local.TodoLocalDataSource
import com.example.assignmentamity.data.network.TodoNetworkDataSource
import com.example.assignmentamity.domain.ToDoItem
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TodoRepositoryTest {

    private val networkDataSource: TodoNetworkDataSource = mock()
    private val localDataSource: TodoLocalDataSource = mock()

    private val repo = TodoRepositoryImpl(networkDataSource, localDataSource)

    @Test
    fun `test fetchToDoList and store items in database`() = runTest {
        //arrange
        val mockList = listOf(
            ToDoEntity(1, 1, "Task 1", false)
        )

        given(networkDataSource.fetchTodoLists()).willReturn(mockList)

        //act
        repo.fetchToDoList()

        //assert
        verify(networkDataSource).fetchTodoLists()
        verify(localDataSource).insertAllTodo(mockList)
    }

    @Test
    fun `test getToDoList get data from database`() = runTest{
        //arrange
        given(localDataSource.getTodoList()).willReturn(
            flowOf(
                listOf(
                    ToDoItem(1, 1, "Task 1", false)
                )
            )
        )

        //act
        val result = repo.getToDoList()

        //assert
        verify(localDataSource).getTodoList()
        result.test {
            Truth.assertThat(awaitItem()[0].title).isEqualTo("Task 1")
            awaitComplete()
        }
    }
}