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
import java.time.LocalDate

val mockTasks = listOf(
    Task(
        id = 1,
        title = "Fix login authentication bug",
        description = "Users can't log in with special characters in password",
        priority = Priority.CRITICAL,
        status = TaskStatus.IN_PROGRESS,
        tags = listOf(TaskTag.BUG_FIX, TaskTag.AUTHENTICATION),
        dueDate = LocalDate.now(),
        estimateHours = 3,
        codeSnippet = """Regex("^[a-zA-Z0-9!@#\$%^&*()_+]+$").matches(password)"""
    ),
    Task(
        id = 2,
        title = "Implement dark theme toggle",
        description = "Add system-aware dark mode with user preference override",
        priority = Priority.HIGH,
        status = TaskStatus.TODO,
        tags = listOf(TaskTag.FEATURE, TaskTag.UI_UX),
        dueDate = LocalDate.now().plusDays(1),
        estimateHours = 5,
        codeSnippet = """AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)"""
    ),
    Task(
        id = 3,
        title = "Code review for payment module",
        description = "Review PR #247 - Stripe integration implementation",
        priority = Priority.MEDIUM,
        status = TaskStatus.TODO,
        tags = listOf(TaskTag.CODE_REVIEW, TaskTag.PAYMENT),
        dueDate = LocalDate.now().plusDays(2),
        estimateHours = 2,
        codeSnippet = """stripe.confirmPayment(this, ConfirmPaymentIntentParams.create(clientSecret))"""
    ),
    Task(
        id = 4,
        title = "Update API documentation",
        description = "Document new user endpoints and authentication flow",
        priority = Priority.LOW,
        status = TaskStatus.BLOCKED,
        tags = listOf(TaskTag.DOCUMENTATION, TaskTag.API),
        dueDate = LocalDate.now().plusDays(5),
        estimateHours = 4,
        codeSnippet = """@GET("/users/{id}") fun getUser(@Path("id") id: String): Call<User>"""
    ),
    Task(
        id = 5,
        title = "Optimize database queries",
        description = "Improve performance of user search functionality",
        priority = Priority.MEDIUM,
        status = TaskStatus.COMPLETED,
        tags = listOf(TaskTag.PERFORMANCE, TaskTag.DATABASE),
        dueDate = LocalDate.now().minusDays(2),
        estimateHours = 6,
        codeSnippet = """SELECT * FROM users WHERE username LIKE '%john%' LIMIT 10"""
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
            dueDate = LocalDate.now().plusDays(3),
            estimateHours = 4,
            codeSnippet = """
            if (isLogin) {
                authService.login(email, password)
            } else {
                    authService.signup(email, password)
            }
            """.trimIndent()
        )
        val current = mockTaskFlow.value.toMutableList()
        current.add(newTask)
        mockTaskFlow.emit(current)
    }

    override suspend fun updateStatusById(taskId: Int, newStatus: String) {
        val current = mockTaskFlow.value.toMutableList()
        val newItemWithUpdatedStatus = current
            .find { it.id == taskId }
            ?.copy(status = TaskStatus.valueOf(newStatus))
            ?: throw Exception("Task not found")
        val updated = current.map { if (it.id == taskId) newItemWithUpdatedStatus else it }
        mockTaskFlow.emit(updated.toMutableList())
    }

    override suspend fun deleteById(taskId: Int) {
        val current = mockTaskFlow.value.toMutableList()
        current.removeIf { it.id == taskId }
        mockTaskFlow.emit(current)
    }
}