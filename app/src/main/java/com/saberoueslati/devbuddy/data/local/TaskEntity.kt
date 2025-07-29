package com.saberoueslati.devbuddy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.TaskStatus

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TaskStatus,
    val tags: String,
    val dueDate: Long,
    val estimateHours: Int,
    val codeSnippet: String
)