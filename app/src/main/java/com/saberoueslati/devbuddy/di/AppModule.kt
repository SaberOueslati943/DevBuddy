package com.saberoueslati.devbuddy.di

import com.saberoueslati.devbuddy.data.repository.MockTaskRepositoryImpl
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTaskRepository(): TaskRepository {
        return MockTaskRepositoryImpl()
    }
}