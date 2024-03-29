package com.example.assignmentamity.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignmentamity.data.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

}