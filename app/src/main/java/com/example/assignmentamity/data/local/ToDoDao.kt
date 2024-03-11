package com.example.assignmentamity.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentamity.data.ToDoResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ToDoResponse>)

    @Query("SELECT * FROM todos")
    fun getTodoList(): Flow<List<ToDoResponse>>
}