package com.saberoueslati.devbuddy.features.home

import com.saberoueslati.devbuddy.domain.model.TaskStatus

data class HomeState(
    val query: String = "",
    val filter: TaskStatus? = null
)