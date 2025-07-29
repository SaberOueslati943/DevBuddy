package com.saberoueslati.devbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saberoueslati.devbuddy.data.local.TaskEntity
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
    suspend fun updateStatusById(taskId: Int, newStatus: String)

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getById(taskId: Int): TaskEntity?

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteById(taskId: Int)
}
