package com.example.assignmentamity.ui.di

import com.example.assignmentamity.data.ToDoRepository
import com.example.assignmentamity.data.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTodoRepository(repositoryImpl: TodoRepositoryImpl): ToDoRepository
}