package com.example.assignmentamity

import com.example.assignmentamity.data.ToDoRepository
import com.example.assignmentamity.domain.RefreshTodoListUseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RefreshTodoListUseCaseTest {

    val repo = mock<ToDoRepository>()

    @Test
    fun `test execute`() = runTest {
        val useCase = RefreshTodoListUseCase(repo)

        //arrange
        given(repo.fetchToDoList()).willReturn(Unit)

        //act
        useCase.execute()

        //assert
        verify(repo).fetchToDoList()
    }
}