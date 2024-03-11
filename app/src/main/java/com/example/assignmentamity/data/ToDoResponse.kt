package com.example.assignmentamity.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "todos")
data class ToDoResponse(
    @Json(name = "userId")
    val userId: Long,

    @PrimaryKey
    @Json(name = "id")
    val id: Long,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "completed")
    val completed: Boolean
)