package com.saberoueslati.devbuddy.di

import android.content.Context
import com.saberoueslati.devbuddy.data.dao.TaskDao
import com.saberoueslati.devbuddy.data.database.DevBuddyDb
import com.saberoueslati.devbuddy.data.repository.TaskRepositoryImpl
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.BG
import com.saberoueslati.devbuddy.utils.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @BG
    @Singleton
    @Provides
    fun provideBGDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @IO
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DevBuddyDb {
        return DevBuddyDb.getInstance(appContext)
    }

    @Provides
    fun provideTaskDao(database: DevBuddyDb): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDao: TaskDao
    ): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}