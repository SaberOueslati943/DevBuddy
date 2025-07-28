package com.saberoueslati.devbuddy.data.repository

import com.saberoueslati.devbuddy.data.dao.TaskDao
import com.saberoueslati.devbuddy.data.local.TaskEntity
import com.saberoueslati.devbuddy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


// TODO: finish this later
class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getTasksAsFlow(): Flow<List<TaskEntity>> {
        return taskDao.getAllAsFlow()
    }

    override fun getTasks(): List<TaskEntity> {
        return taskDao.getAll()
    }

    override suspend fun addTask(task: TaskEntity) {
        return taskDao.insert(task)
    }

    override suspend fun markDone(taskId: Int) {
        return taskDao.markDone(taskId)
    }
}