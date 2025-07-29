package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.domain.usecase.DeleteTaskByIdUseCase
import com.saberoueslati.devbuddy.domain.usecase.GetAllTasksAsFlowUseCase
import com.saberoueslati.devbuddy.domain.usecase.UpdateStatusUseCase
import com.saberoueslati.devbuddy.utils.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

data class HomeUseCases(
    val getAllTasksAsFlowUseCase: GetAllTasksAsFlowUseCase,
    val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
    val updateStatusUseCase: UpdateStatusUseCase
)

@Module
@InstallIn(ViewModelComponent::class)
internal object HomeModule {
    @Provides
    @ViewModelScoped
    fun provideHomeUseCases(
        taskRepository: TaskRepository,
        @IO io: CoroutineDispatcher
    ): HomeUseCases {
        return HomeUseCases(
            getAllTasksAsFlowUseCase = GetAllTasksAsFlowUseCase(
                taskRepository = taskRepository
            ),
            deleteTaskByIdUseCase = DeleteTaskByIdUseCase(
                taskRepository = taskRepository,
                io = io
            ),
            updateStatusUseCase = UpdateStatusUseCase(
                taskRepository = taskRepository,
                io = io
            )
        )
    }
}