package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.domain.usecase.GetAllTasksAsFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

data class HomeUseCases(
    val getAllTasksAsFlowUseCase: GetAllTasksAsFlowUseCase
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
            getAllTasksAsFlowUseCase = GetAllTasksAsFlowUseCase(
                taskRepository = taskRepository
            )
        )
    }
}