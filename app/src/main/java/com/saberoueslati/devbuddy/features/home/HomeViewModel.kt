package com.saberoueslati.devbuddy.features.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {
    val tasks = useCases.getAllTasksUseCase.execute()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
}