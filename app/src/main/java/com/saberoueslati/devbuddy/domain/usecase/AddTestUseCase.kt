package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.mapper.toEntity
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.Outcome
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddTaskUseCase(
    private val taskRepository: TaskRepository,
    private val io: CoroutineDispatcher
) {
    suspend fun execute(task: Task): Outcome<Unit, Exception> = withContext(io) {
        Outcome.wrapException { taskRepository.addTask(task.toEntity()) }
    }
}