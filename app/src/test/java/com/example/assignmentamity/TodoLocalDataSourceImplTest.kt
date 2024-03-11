package com.example.assignmentamity

import app.cash.turbine.test
import com.example.assignmentamity.data.ToDoResponse
import com.example.assignmentamity.data.local.ToDoDao
import com.example.assignmentamity.data.local.TodoLocalDataSourceImpl
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TodoLocalDataSourceImplTest {

    private val dao = mock<ToDoDao>()

    @Test
    fun `test getTodoList get data from dao`() = runTest {
        val dataSource = TodoLocalDataSourceImpl(dao)

        val mockList = listOf(
            ToDoResponse(1, 1, "task1", false)
        )
        given(dao.getTodoList()).willReturn(flowOf(mockList))

        dataSource.getTodoList().test {
            Truth.assertThat(awaitItem()).isNotEmpty()
            awaitComplete()
        }
        verify(dao).getTodoList()

    }
}