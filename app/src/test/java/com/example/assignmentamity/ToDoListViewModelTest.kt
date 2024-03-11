package com.example.assignmentamity

import app.cash.turbine.test
import com.example.assignmentamity.base.MainDispatcherRule
import com.example.assignmentamity.data.ToDoRepository
import com.example.assignmentamity.domain.GetTodoListUseCase
import com.example.assignmentamity.domain.RefreshTodoListUseCase
import com.example.assignmentamity.domain.ToDoItem
import com.example.assignmentamity.ui.ToDoListViewModel
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToDoListViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    private val getTodoListUseCase = mock<GetTodoListUseCase>()
    private val refreshTodoListUseCase = mock<RefreshTodoListUseCase>()

    @Before
    fun setUp(){
        given(getTodoListUseCase.execute()).willReturn(flowOf(listOf()))
    }

    @Test
    fun `test fetchTodoList refresh data from network`() = runTest {

        //arrange
        given(refreshTodoListUseCase.execute()).willReturn(Unit)
        given(getTodoListUseCase.execute()).willReturn(
            flowOf(listOf(ToDoItem(1, 1, "Task 1", false)))
        )
        val viewModel = ToDoListViewModel(refreshTodoListUseCase, getTodoListUseCase)

        //act
        viewModel.fetchTodoList()

        //assert
        verify(refreshTodoListUseCase).execute()
        val state = viewModel.todoViewStateFlow.value
        Truth.assertThat(state.isError).isNull()
        Truth.assertThat(state.isLoading).isEqualTo(false)
        Truth.assertThat(state.items[0].title).isEqualTo("Task 1")
    }

    @Test
    fun `emit loading state correctly`() = runTest {
        //arrange
        val viewModel = ToDoListViewModel(refreshTodoListUseCase, getTodoListUseCase)

        //assert
        val state = viewModel.todoViewStateFlow.value
        Truth.assertThat(state.isLoading).isTrue()
    }

    @Test(expected = Exception::class)
    fun `test fetchTodoList failed and emit error state`() = runTest {

        //arrange
        given(refreshTodoListUseCase.execute()).willThrow(Exception())
        given(getTodoListUseCase.execute()).willReturn(
            flowOf(listOf())
        )
        val viewModel = ToDoListViewModel(refreshTodoListUseCase, getTodoListUseCase)

        //act
        viewModel.fetchTodoList()

        //assert
        verify(refreshTodoListUseCase).execute()
        val state = viewModel.todoViewStateFlow.value
        Truth.assertThat(state.isError).isNotNull()
        Truth.assertThat(state.items).isEmpty()
    }

    @Test
    fun `test viewModel get data from database and update stateflow`() = runTest {

        //arrange
        given(getTodoListUseCase.execute()).willReturn(
            flowOf(listOf(ToDoItem(1, 1, "Task 1", false)))
        )
        val viewModel = ToDoListViewModel(refreshTodoListUseCase, getTodoListUseCase)

        //assert
        val state = viewModel.todoViewStateFlow.value
        Truth.assertThat(state.items[0].title).isEqualTo("Task 1")
    }
}