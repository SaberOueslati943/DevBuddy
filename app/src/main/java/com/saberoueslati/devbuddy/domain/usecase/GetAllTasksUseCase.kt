package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.mapper.toModel
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.Outcome

class GetAllTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(): Outcome<List<Task>, Exception> {
        return Outcome.wrapException { taskRepository.getTasks().map { it.toModel() } }
    }
}