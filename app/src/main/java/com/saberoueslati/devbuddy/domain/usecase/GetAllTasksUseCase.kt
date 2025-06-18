package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.repository.TaskRepository

class GetAllTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(): List<Task> {
        return taskRepository.getTasks()
    }
}