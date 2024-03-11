package com.example.assignmentamity.data

import retrofit2.http.GET

interface ToDoService {

    @GET("todos")
    suspend fun getToDoList(): List<ToDoEntity>
}