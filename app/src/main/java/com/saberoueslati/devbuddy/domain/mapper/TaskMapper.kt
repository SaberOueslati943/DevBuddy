package com.saberoueslati.devbuddy.domain.mapper

import com.saberoueslati.devbuddy.data.local.TaskEntity
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskTag
import java.util.Date

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        priority = priority,
        status = status,
        tags = tags.joinToString(",") { it.name },
        dueDate = dueDate.time,
        estimateHours = estimateHours,
        hasCodeSnippet = hasCodeSnippet
    )
}

fun TaskEntity.toModel(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        priority = priority,
        status = status,
        tags = tags.split(",").map { TaskTag.valueOf(it) },
        dueDate = Date(dueDate),
        estimateHours = estimateHours,
        hasCodeSnippet = hasCodeSnippet
    )
}