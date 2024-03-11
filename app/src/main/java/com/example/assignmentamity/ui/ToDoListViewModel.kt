package com.example.assignmentamity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentamity.domain.GetTodoListUseCase
import com.example.assignmentamity.domain.RefreshTodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val refreshTodoListUseCase: RefreshTodoListUseCase,
    private val getTodoListUseCase: GetTodoListUseCase
) : ViewModel() {

    private val todoMutableStateFlow = MutableStateFlow(ToDoViewState(isLoading = true))
    val todoViewStateFlow = todoMutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getTodoListUseCase.execute()
                .onEach { list ->
                    todoMutableStateFlow.update {
                        todoMutableStateFlow.value.copy(items = list)
                    }
                }
                .distinctUntilChanged()
                .stateIn(viewModelScope)
        }
    }

    fun fetchTodoList() {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            todoMutableStateFlow.update {
                todoMutableStateFlow.value.copy(isError = throwable, isLoading = false)
            }
        }
        viewModelScope.launch(errorHandler) {
            refreshTodoListUseCase.execute()
            todoMutableStateFlow.update {
                todoMutableStateFlow.value.copy(isLoading = false)
            }
        }
    }
}