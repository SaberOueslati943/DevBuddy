package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus

sealed class HomeAction {
    data class OnSearchQueryChanged(val newQuery: String) : HomeAction()
    data class OnFilterSelected(val newFilter: TaskStatus?) : HomeAction()
    data class OnTaskClicked(val task: Task) : HomeAction()
    data class OnDeleteTaskClicked(val task: Task) : HomeAction()
    data class OnTaskStatusClicked(val task: Task, val newStatus: TaskStatus) : HomeAction()
    object OnAddTaskClicked : HomeAction()
    object OnClearSearchQueryClicked : HomeAction()
}