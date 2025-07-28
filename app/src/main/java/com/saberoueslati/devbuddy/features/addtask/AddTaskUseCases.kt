package com.saberoueslati.devbuddy.features.addtask

import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.domain.usecase.AddTaskUseCase
import com.saberoueslati.devbuddy.utils.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

data class AddTaskUseCases(
    val addTaskUseCase: AddTaskUseCase
)


@Module
@InstallIn(ViewModelComponent::class)
internal object AddTaskModule {
    @Provides
    @ViewModelScoped
    fun provideHomeUseCases(
        taskRepository: TaskRepository,
        @IO io: CoroutineDispatcher
    ): AddTaskUseCases {
        return AddTaskUseCases(
            addTaskUseCase = AddTaskUseCase(
                taskRepository = taskRepository,
                io = io
            )
        )
    }
}