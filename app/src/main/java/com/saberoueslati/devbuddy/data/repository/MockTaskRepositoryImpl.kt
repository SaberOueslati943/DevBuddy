package com.saberoueslati.devbuddy.data.repository

import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.repository.TaskRepository

val mockTasks = listOf(
    Task(
        name = "Write report",
        description = "Finish the monthly sales report and send it to the manager.",
        done = false
    ),
    Task(
        name = "Team meeting",
        description = "Weekly sync-up with the design and dev team.",
        done = true
    ),
    Task(
        name = "Code review",
        description = "Review pull requests assigned in GitHub.",
        done = false
    ),
    Task(
        name = "Client call",
        description = "Call with the client to go over feedback.",
        done = true
    ),
    Task(
        name = "Backup database",
        description = "Ensure all project data is backed up securely.",
        done = false
    )
)

class MockTaskRepositoryImpl : TaskRepository {
    override fun getTasks(): List<Task> {
        return mockTasks
    }

    override suspend fun addTask(task: Task) {
        // do nothing
    }

    override suspend fun markDone(taskId: Int) {
        // do nothing
    }
}