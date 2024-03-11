package com.example.assignmentamity.ui.di

import com.example.assignmentamity.data.local.TodoLocalDataSource
import com.example.assignmentamity.data.local.TodoLocalDataSourceImpl
import com.example.assignmentamity.data.network.TodoNetworkDataSource
import com.example.assignmentamity.data.network.TodoNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindTodoNetworkDataSource(networkDataSource: TodoNetworkDataSourceImpl)
            : TodoNetworkDataSource

    @Singleton
    @Binds
    abstract fun bindTodoLocalDataSource(localDataSource: TodoLocalDataSourceImpl)
            : TodoLocalDataSource
}