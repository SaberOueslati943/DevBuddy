package com.saberoueslati.devbuddy.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {
    val tasks = useCases.getAllTasksUseCase.execute()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _reaction = MutableSharedFlow<HomeReaction>()
    val reaction: SharedFlow<HomeReaction> get() = _reaction.asSharedFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.OnAddTaskClicked -> onAddTaskClicked()
            HomeAction.OnClearSearchQueryClicked -> onClearSearchQueryClicked()
            is HomeAction.OnFilterSelected -> onFilterSelected(action.newFilter)
            is HomeAction.OnSearchQueryChanged -> onSearchQueryChanged(action.newQuery)
            is HomeAction.OnTaskClicked -> onTaskClicked(action.task)
        }
    }

    private fun onAddTaskClicked() = viewModelScope.launch {
        _reaction.emit(HomeReaction.OnAddTaskClicked)
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