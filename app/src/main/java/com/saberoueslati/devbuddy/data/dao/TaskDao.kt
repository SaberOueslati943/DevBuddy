package com.saberoueslati.devbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saberoueslati.devbuddy.data.local.TaskEntity
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task")
    fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Query("UPDATE task SET status = :newStatus WHERE id = :taskId")
    suspend fun markDone(taskId: Int, newStatus: String = TaskStatus.COMPLETED.name)

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getById(taskId: Int): TaskEntity?
}
