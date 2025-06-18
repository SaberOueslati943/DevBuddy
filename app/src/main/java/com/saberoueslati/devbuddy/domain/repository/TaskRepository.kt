package com.saberoueslati.devbuddy.domain.repository

import com.saberoueslati.devbuddy.domain.model.Task

interface TaskRepository {
    fun getTasks(): List<Task>
    suspend fun addTask(task: Task)
    suspend fun markDone(taskId: Int)
}