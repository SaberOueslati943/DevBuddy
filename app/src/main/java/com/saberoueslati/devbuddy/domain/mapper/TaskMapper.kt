package com.saberoueslati.devbuddy.domain.mapper

import com.saberoueslati.devbuddy.data.local.TaskEntity
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskTag
import java.time.Instant
import java.time.ZoneId
import java.util.Date

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        priority = priority,
        status = status,
        tags = tags.joinToString(",") { it.name },
        dueDate = Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).time,
        estimateHours = estimateHours,
        codeSnippet = codeSnippet.trim()
    )
}

fun TaskEntity.toModel(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        priority = priority,
        status = status,
        tags = if (tags.isNotEmpty()) tags.split(",").map { TaskTag.valueOf(it) } else emptyList(),
        dueDate = Instant.ofEpochMilli(dueDate).atZone(ZoneId.systemDefault()).toLocalDate(),
        estimateHours = estimateHours,
        codeSnippet = codeSnippet
    )
}