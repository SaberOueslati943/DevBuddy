package com.saberoueslati.devbuddy.domain.usecase

import com.saberoueslati.devbuddy.domain.mapper.toModel
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import com.saberoueslati.devbuddy.utils.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTasksAsFlowUseCase(
    private val taskRepository: TaskRepository
) {
    fun execute(): Outcome<Flow<List<Task>>, Exception> {
        return Outcome.wrapException {
            taskRepository
                .getTasksAsFlow()
                .map { flow ->
                    flow.map {
                        it.toModel()
                    }
                }
        }
    }
}