package com.saberoueslati.devbuddy.domain.repository

import com.saberoueslati.devbuddy.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasksAsFlow(): Flow<List<TaskEntity>>
    fun getTasks(): List<TaskEntity>
    suspend fun addTask(task: TaskEntity)
    suspend fun updateStatusById(taskId: Int, newStatus: String)
    suspend fun deleteById(taskId: Int)
}