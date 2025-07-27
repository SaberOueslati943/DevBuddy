package com.saberoueslati.devbuddy.features.addtask

import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.model.TaskTag
import java.time.LocalDate

sealed class AddTaskAction {
    data class OnTitleChanged(val newTitle: String) : AddTaskAction()
    data class OnDescriptionChanged(val newDescription: String) : AddTaskAction()
    data class OnPriorityChanged(val newPriority: Priority) : AddTaskAction()
    data class OnStatusChanged(val newStatus: TaskStatus) : AddTaskAction()
    data class OnTagClicked(val newTag: TaskTag) : AddTaskAction()
    data class OnDueDateChanged(val newDueDate: LocalDate?) : AddTaskAction()
    data class OnEstimateChanged(val newEstimate: Int) : AddTaskAction()
    object OnSaveTaskClicked : AddTaskAction()
    object OnBackClicked : AddTaskAction()
    object OnCodeSnippetClicked : AddTaskAction()
    data class OnCodeSnippetChanged(val newCodeSnippet: String) : AddTaskAction()
}