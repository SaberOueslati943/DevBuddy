package com.saberoueslati.devbuddy.features.home

import androidx.lifecycle.ViewModel
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {
    val tasks = useCases.getAllTasksUseCase.execute()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.OnAddTaskClicked -> onAddTaskClicked()
            HomeAction.OnClearSearchQueryClicked -> onClearSearchQueryClicked()
            is HomeAction.OnFilterSelected -> onFilterSelected(action.newFilter)
            is HomeAction.OnSearchQueryChanged -> onSearchQueryChanged(action.newQuery)
            is HomeAction.OnTaskClicked -> onTaskClicked(action.task)
        }
    }

    private fun onAddTaskClicked() {
        // TODO: Navigate to add task screen
    }

    private fun onTaskClicked(task: Task) {
        // TODO: Navigate to task details screen
    }

    private fun onSearchQueryChanged(query: String) {
        _state.update {
            it.copy(query = query)
        }
    }

    private fun onFilterSelected(status: TaskStatus?) {
        _state.update {
            it.copy(filter = status)
        }
    }

    private fun onClearSearchQueryClicked() {
        _state.update {
            it.copy(query = "")
        }
    }
}