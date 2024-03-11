package com.example.assignmentamity.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignmentamity.data.ToDoResponse

@Database(entities = [ToDoResponse::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

}