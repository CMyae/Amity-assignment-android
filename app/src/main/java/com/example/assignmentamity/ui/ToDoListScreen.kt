package com.example.assignmentamity.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ToDoListScreen(
    viewModel: ToDoListViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val todoList = viewModel.todoViewStateFlow.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchTodoList()
    }

    showDialog = todoList.isLoading
    showError = todoList.isError != null

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "TODO LIST",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        if (todoList.items.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(todoList.items) { item ->
                    TodoComponent(title = item.title, completed = item.completed)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    LaunchedEffect(key1 = showError) {
        if (showError) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
        }
    }

    if (showDialog) LoadingView()
}

@Composable
fun TodoComponent(title: String, completed: Boolean) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = "Title : $title")
            Spacer(modifier = Modifier.height(4.dp))
            val status = if (completed) {
                "Completed"
            } else {
                "Incomplete"
            }
            Text(text = "Status : $status")
        }
    }
}

@Composable
fun LoadingView() {
    Dialog(
        onDismissRequest = { },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .size(100.dp)
                .background(LightGray, shape = RoundedCornerShape(8.dp))
        ) {
            CircularProgressIndicator()
        }
    }
}