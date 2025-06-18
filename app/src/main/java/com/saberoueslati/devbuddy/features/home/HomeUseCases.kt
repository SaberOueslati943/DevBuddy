package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.domain.usecase.GetAllTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

data class HomeUseCases(
    val getAllTasksUseCase: GetAllTasksUseCase
)

@Module
@InstallIn(ViewModelComponent::class)
internal object HomeModule {
    @Provides
    @ViewModelScoped
    fun provideHomeUseCases(
        taskRepository: TaskRepository
    ): HomeUseCases {
        return HomeUseCases(
            getAllTasksUseCase = GetAllTasksUseCase(
                taskRepository = taskRepository
            )
        )
    }
}