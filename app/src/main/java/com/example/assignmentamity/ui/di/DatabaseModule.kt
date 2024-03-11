package com.example.assignmentamity.ui.di

import android.content.Context
import androidx.room.Room
import com.example.assignmentamity.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, TodoDatabase::class.java, "todo_database"
    ).build()

    @Singleton
    @Provides
    fun provideTodoDao(database: TodoDatabase) = database.todoDao()
}