package com.saberoueslati.devbuddy.features.addtask

import androidx.lifecycle.ViewModel
import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.model.TaskTag
import com.saberoueslati.devbuddy.utils.BG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    @BG bg: CoroutineDispatcher
) : ViewModel() {

    val viewModelScope: CoroutineScope = CoroutineScope(SupervisorJob() + bg)

    private val _state = MutableStateFlow(AddTaskState())
    val state = _state.asStateFlow()

    private val _reaction = MutableSharedFlow<AddTaskReaction>()
    val reaction: SharedFlow<AddTaskReaction> get() = _reaction.asSharedFlow()

    fun onAction(action: AddTaskAction) {
        when (action) {
            AddTaskAction.OnBackClicked -> onBackClicked()
            is AddTaskAction.OnCodeSnippetChanged -> onCodeSnippetChanged(action.newCodeSnippet)
            AddTaskAction.OnCodeSnippetClicked -> onCodeSnippetClicked()
            is AddTaskAction.OnDescriptionChanged -> onDescriptionChanged(action.newDescription)
            is AddTaskAction.OnDueDateChanged -> onDueDateChanged(action.newDueDate)
            is AddTaskAction.OnEstimateChanged -> onEstimateChanged(action.newEstimate)
            is AddTaskAction.OnPriorityChanged -> onPriorityChanged(action.newPriority)
            AddTaskAction.OnSaveTaskClicked -> onSaveTaskClicked()
            is AddTaskAction.OnStatusChanged -> onStatusChanged(action.newStatus)
            is AddTaskAction.OnTagClicked -> onTagClicked(action.newTag)
            is AddTaskAction.OnTitleChanged -> onTitleChanged(action.newTitle)
        }
    }

    private fun onTitleChanged(newTitle: String) = viewModelScope.launch {
        _state.update { it.copy(title = newTitle) }
    }

    private fun onTagClicked(newTag: TaskTag) = viewModelScope.launch {
        _state.update { currentState ->
            val updatedTags = if (currentState.tags.contains(newTag)) {
                currentState.tags - newTag
            } else {
                currentState.tags + newTag
            }
            currentState.copy(tags = updatedTags)
        }
    }

    private fun onStatusChanged(newStatus: TaskStatus) = viewModelScope.launch {
        _state.update { it.copy(status = newStatus) }
    }

    private fun onSaveTaskClicked() = viewModelScope.launch {
        val currentState = _state.value

        if (currentState.title.isBlank()) {
            _reaction.emit(AddTaskReaction.InvalidTitle)
            return@launch
        }

        // TODO: use a usecase to save the task to room

        _reaction.emit(AddTaskReaction.OnSaveTaskCompleted)
    }

    private fun onPriorityChanged(newPriority: Priority) = viewModelScope.launch {
        _state.update { it.copy(priority = newPriority) }
    }

    private fun onEstimateChanged(newEstimate: Int) = viewModelScope.launch {
        _state.update { it.copy(estimate = newEstimate) }
    }

    private fun onDueDateChanged(newDueDate: LocalDate?) = viewModelScope.launch {
        _state.update { it.copy(dueDate = newDueDate) }
    }

    private fun onDescriptionChanged(newDescription: String) = viewModelScope.launch {
        _state.update { it.copy(description = newDescription) }
    }

    private fun onCodeSnippetClicked() = viewModelScope.launch {
        _state.update { it.copy(showCodeSnippet = !it.showCodeSnippet) }
    }

    private fun onCodeSnippetChanged(newCodeSnippet: String) = viewModelScope.launch {
        _state.update { it.copy(codeSnippet = newCodeSnippet) }
    }

    private fun onBackClicked() = viewModelScope.launch {
        _reaction.emit(AddTaskReaction.OnBackClicked)
    }
}