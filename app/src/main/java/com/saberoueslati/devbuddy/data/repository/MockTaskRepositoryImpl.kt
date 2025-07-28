package com.saberoueslati.devbuddy.data.repository

import com.saberoueslati.devbuddy.data.local.TaskEntity
import com.saberoueslati.devbuddy.domain.mapper.toEntity
import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.model.TaskTag
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date

val mockTasks = listOf(
    Task(
        id = 1,
        title = "Fix login authentication bug",
        description = "Users can't log in with special characters in password",
        priority = Priority.CRITICAL,
        status = TaskStatus.IN_PROGRESS,
        tags = listOf(TaskTag.BUG_FIX, TaskTag.AUTHENTICATION),
        dueDate = Date(),
        estimateHours = 3,
        hasCodeSnippet = true
    ),
    Task(
        id = 2,
        title = "Implement dark theme toggle",
        description = "Add system-aware dark mode with user preference override",
        priority = Priority.HIGH,
        status = TaskStatus.TODO,
        tags = listOf(TaskTag.FEATURE, TaskTag.UI_UX),
        dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) }.time,
        estimateHours = 5,
        hasCodeSnippet = false
    ),
    Task(
        id = 3,
        title = "Code review for payment module",
        description = "Review PR #247 - Stripe integration implementation",
        priority = Priority.MEDIUM,
        status = TaskStatus.TODO,
        tags = listOf(TaskTag.CODE_REVIEW, TaskTag.PAYMENT),
        dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 2) }.time,
        estimateHours = 2,
        hasCodeSnippet = false
    ),
    Task(
        id = 4,
        title = "Update API documentation",
        description = "Document new user endpoints and authentication flow",
        priority = Priority.LOW,
        status = TaskStatus.BLOCKED,
        tags = listOf(TaskTag.DOCUMENTATION, TaskTag.API),
        dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 5) }.time,
        estimateHours = 4,
        hasCodeSnippet = false
    ),
    Task(
        id = 5,
        title = "Optimize database queries",
        description = "Improve performance of user search functionality",
        priority = Priority.MEDIUM,
        status = TaskStatus.COMPLETED,
        tags = listOf(TaskTag.PERFORMANCE, TaskTag.DATABASE),
        dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -2) }.time,
        estimateHours = 6,
        hasCodeSnippet = true
    )
)

class MockTaskRepositoryImpl : TaskRepository {

    private val mockTaskFlow = MutableStateFlow(mockTasks.toMutableList())

    override fun getTasksAsFlow(): Flow<List<TaskEntity>> {
        return mockTaskFlow.map { tasks -> tasks.map { it.toEntity() } }
    }

    override fun getTasks(): List<TaskEntity> {
        return mockTasks.map { it.toEntity() }
    }

    override suspend fun addTask(task: TaskEntity) {
        val newTask = Task(
            id = 6,
            title = "Refactor authentication flow",
            description = "Simplify login/signup logic and remove duplicated code",
            priority = Priority.HIGH,
            status = TaskStatus.TODO,
            tags = listOf(TaskTag.FEATURE, TaskTag.AUTHENTICATION),
            dueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) }.time,
            estimateHours = 4,
            hasCodeSnippet = true
        )
        val current = mockTaskFlow.value.toMutableList()
        current.add(newTask)
        mockTaskFlow.emit(current)
    }

    override suspend fun markDone(taskId: Int) {
        val updated = mockTaskFlow.value.map {
            if (it.id == taskId) it.copy(status = TaskStatus.COMPLETED) else it
        }
        mockTaskFlow.emit(updated.toMutableList())
    }
}