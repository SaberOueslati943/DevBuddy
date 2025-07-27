package com.saberoueslati.devbuddy.features.addtask

import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.model.TaskTag
import java.time.LocalDate

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val priority: Priority = Priority.MEDIUM,
    val status: TaskStatus = TaskStatus.TODO,
    val tags: List<TaskTag> = emptyList(),
    val dueDate: LocalDate? = null,
    val estimate: Int = 0,
    val showCodeSnippet: Boolean = false,
    val codeSnippet: String = ""
)