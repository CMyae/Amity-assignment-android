package com.example.assignmentamity.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentamity.data.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ToDoEntity>)

    @Query("SELECT * FROM todos")
    fun getTodoList(): Flow<List<ToDoEntity>>
}