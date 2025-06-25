package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus

sealed class HomeAction {
    data class OnSearchQueryChanged(val newQuery: String) : HomeAction()
    data class OnFilterSelected(val newFilter: TaskStatus?) : HomeAction()
    data class OnTaskClicked(val task: Task) : HomeAction()
    object OnAddTaskClicked : HomeAction()
    object OnClearSearchQueryClicked : HomeAction()
}