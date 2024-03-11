package com.example.assignmentamity

import com.example.assignmentamity.data.ToDoResponse
import com.example.assignmentamity.data.ToDoService
import com.example.assignmentamity.data.network.TodoNetworkDataSourceImpl
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TodoNetworkDataSourceImplTest {

    private val service = mock<ToDoService>()

    @Test
    fun `test fetchTodoLists get data from api`() = runTest {
        val dataSource = TodoNetworkDataSourceImpl(service)

        given(service.getToDoList()).willReturn(
            listOf(
                ToDoResponse(1, 1, "task 1", true)
            )
        )

        val result = dataSource.fetchTodoLists()

        verify(service).getToDoList()
        Truth.assertThat(result[0].title).isEqualTo("task 1")
    }
}