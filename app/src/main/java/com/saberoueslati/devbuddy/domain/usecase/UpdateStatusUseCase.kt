package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.Outcome
import kotlinx.coroutines.CoroutineDispatcher

class UpdateStatusUseCase(
    private val taskRepository: TaskRepository,
    private val io: CoroutineDispatcher
) {
    suspend fun execute(taskId: Int, newStatus: TaskStatus): Outcome<Unit, Exception> {
        return Outcome.wrapException {
            taskRepository.updateStatusById(
                taskId = taskId,
                newStatus = newStatus.name
            )
        }
    }
}