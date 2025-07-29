package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.Outcome
import kotlinx.coroutines.CoroutineDispatcher

class DeleteTaskByIdUseCase(
    private val taskRepository: TaskRepository,
    private val io: CoroutineDispatcher
) {
    suspend fun execute(taskId: Int): Outcome<Unit, Exception> {
        return Outcome.wrapException { taskRepository.deleteById(taskId = taskId) }
    }
}