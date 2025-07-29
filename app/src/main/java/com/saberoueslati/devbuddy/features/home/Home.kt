package com.saberoueslati.devbuddy.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.data.repository.mockTasks
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.features.addtask.AddTaskRoute
import com.saberoueslati.devbuddy.features.home.subviews.AddTaskButton
import com.saberoueslati.devbuddy.features.home.subviews.Filters
import com.saberoueslati.devbuddy.features.home.subviews.Search
import com.saberoueslati.devbuddy.features.home.subviews.TaskItem
import com.saberoueslati.devbuddy.features.home.subviews.TotalStatusOfTasks
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.Spacing
import com.saberoueslati.devbuddy.ui.theme.onPrimary
import com.saberoueslati.devbuddy.ui.theme.primary
import com.saberoueslati.devbuddy.ui.theme.secondary
import com.saberoueslati.devbuddy.utils.React

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    backStack: NavBackStack,
    vm: HomeViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()
    val tasks by vm.tasks.collectAsState(initial = emptyList())

    vm.reaction.React {
        when (it) {
            HomeReaction.OnAddTaskClicked -> {
                backStack.add(AddTaskRoute)
            }
        }
    }


    HomeContent(
        state = state,
        onAction = vm::onAction,
        tasks = tasks
    )
}

@ExperimentalMaterial3Api
@Composable
fun HomeContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    tasks: List<Task>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.primary
                ),
                title = {
                    Text(
                        text = stringResource(R.string.dev_tasks),
                        color = Color.onPrimary
                    )
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
            ) {
                HeaderItems(state, onAction, tasks)

                if (tasks.isNotEmpty()) {
                    // List
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(tasks.filter { state.filter == null || it.status == state.filter }
                            .filter {
                                if (state.query.isNotEmpty()) {
                                    it.title.lowercase().contains(state.query, true)
                                } else {
                                    true
                                }
                            }) { task ->
                            TaskItem(task, onAction)
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
                        ) {
                            AppText(text = stringResource(R.string.no_tasks))
                            Icon(Icons.Default.Celebration, contentDescription = "Celebration", tint = Color.secondary)
                        }
                    }
                }
            }
        }

        AddTaskButton(onAction)
    }
}

@Composable
private fun HeaderItems(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    tasks: List<Task>
) {
    Column(
        modifier = Modifier.background(Color.primary)
    ) {
        Search(state, onAction)

        Filters(state, onAction)

        TotalStatusOfTasks(tasks)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun HomePreview() {
    DevBuddyTheme {
        HomeContent(
            HomeState(),
            {},
            mockTasks
        )
    }
}