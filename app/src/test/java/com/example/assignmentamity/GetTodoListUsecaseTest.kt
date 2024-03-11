package com.example.assignmentamity

import app.cash.turbine.test
import com.example.assignmentamity.data.ToDoRepository
import com.example.assignmentamity.domain.GetTodoListUseCase
import com.example.assignmentamity.domain.ToDoItem
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTodoListUsecaseTest {

    val repo = mock<ToDoRepository>()

    @Test
    fun `test execute get data from repository`() = runTest {
        val useCase = GetTodoListUseCase(repo)

        //arrange
        given(repo.getToDoList()).willReturn(
            flowOf(
                listOf(
                    ToDoItem(1, 1, "Task 1", false)
                )
            )
        )

        //act
        val flow = useCase.execute()

        //assert
        verify(repo).getToDoList()
        flow.test {
            Truth.assertThat(awaitItem()).isNotEmpty()
            awaitComplete()
        }
    }
}