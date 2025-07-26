package com.saberoueslati.devbuddy.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.data.repository.MockTaskRepositoryImpl
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.features.addtask.AddTaskRoute
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.Spacing
import com.saberoueslati.devbuddy.ui.theme.onPrimary
import com.saberoueslati.devbuddy.ui.theme.primary
import com.saberoueslati.devbuddy.utils.React
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    backStack: NavBackStack,
    vm: HomeViewModel = hiltViewModel()
) {
    vm.reaction.React {
        when (it) {
            HomeReaction.OnAddTaskClicked -> {
                backStack.add(AddTaskRoute)
            }
        }
    }

    val state by vm.state.collectAsState()
    HomeContent(
        state = state,
        onAction = vm::onAction,
        tasks = vm.tasks
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
            }
        }

        AddTaskButton(onAction)
    }
}

@Composable
private fun AddTaskButton(onAction: (HomeAction) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onAction.invoke(HomeAction.OnAddTaskClicked) },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(16.dp),
            containerColor = Color(0xFF2563EB)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                tint = Color.White
            )
        }
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

@Composable
private fun TotalStatusOfTasks(tasks: List<Task>) {
    Row(
        modifier = Modifier
            .padding(Spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        Text(
            text = stringResource(
                if (tasks.size > 1) R.string.total_tasks_total else R.string.total_tasks_total_one,
                tasks.size
            ),
            color = Color(0xFF9ca39f)
        )
        Filler()
        Text(
            text = stringResource(
                R.string.total_tasks_done,
                tasks.count { it.status == TaskStatus.COMPLETED }
            ),
            color = Color(0xFF9ca39f)
        )
    }
}

@Composable
private fun Filters(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        item {
            FilterItem(
                isSelected = state.filter == null,
                label = stringResource(R.string.filter_all)
            ) {
                onAction.invoke(HomeAction.OnFilterSelected(null))
            }
        }

        items(TaskStatus.entries) { status ->
            if (status != TaskStatus.COMPLETED) {
                FilterItem(
                    isSelected = state.filter == status,
                    label = stringResource(status.resourceId)
                ) {
                    onAction.invoke(HomeAction.OnFilterSelected(status))
                }
            }
        }
    }
}

@Composable
private fun Search(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    AppTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.m),
        placeholder = stringResource(R.string.search_tasks),
        value = state.query,
        onValueChange = { newValue: String ->
            onAction.invoke(HomeAction.OnSearchQueryChanged(newValue))
        },
        type = AppTextFieldType.Search(
            onClearClicked = {
                onAction.invoke(HomeAction.OnClearSearchQueryClicked)
            }
        )
    )
}

@Composable
fun TaskItem(task: Task, onAction: (HomeAction) -> Unit) {
    // TODO: Use Constraint Layout here later
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource = null) {
                onAction.invoke(HomeAction.OnTaskClicked(task))
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(Spacing.l)
    ) {
        Column(
            modifier = Modifier.padding(Spacing.l)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = Spacing.xxs)
                        .size(Spacing.m)
                        .clip(CircleShape)
                        .background(task.priority.color)
                )

                Filler(Spacing.m)

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        color = if (task.status == TaskStatus.COMPLETED) Color(0xFF6B7280) else Color.White,
                        fontSize = 16.sp,
                        textDecoration = if (task.status == TaskStatus.COMPLETED) TextDecoration.LineThrough else TextDecoration.None,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Filler(Spacing.xxs)

                    Text(
                        text = task.description,
                        color = Color(0xFF9CA3AF),
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    onClick = {
                        // TODO: use swipe or three dots to complete tasks?
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color(0xFF9CA3AF)
                    )
                }
            }

            Filler(Spacing.m)

            // Tags
            LazyRow(horizontalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                items(task.tags) { tag ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = tag.backgroundColor.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(Spacing.xxs)
                            )
                            .border(
                                width = 1.dp,
                                color = tag.color.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(Spacing.xxs)
                            )
                            .padding(horizontal = Spacing.xs, vertical = Spacing.xxs)
                    ) { Text(text = stringResource(tag.resourceId), color = tag.color, fontSize = 12.sp) }
                }
            }
            Filler(Spacing.xxs)
            // Task Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.l),
                    verticalAlignment = Alignment.CenterVertically
                ) {                    // Due Date
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Due Date",
                            tint = Color(0xFF9CA3AF),
                            modifier = Modifier.size(Spacing.l)
                        )
                        Text(
                            text = SimpleDateFormat(
                                "MMM dd",
                                Locale.getDefault()
                            ).format(task.dueDate), color = Color(0xFF9CA3AF), fontSize = 12.sp
                        )
                    }
                    // Estimate
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Estimate",
                            tint = Color(0xFF9CA3AF),
                            modifier = Modifier.size(Spacing.l)
                        )
                        Text(
                            text = "${task.estimateHours}h",
                            color = Color(0xFF9CA3AF),
                            fontSize = 12.sp
                        )
                    }
                    // Code Snippet Indicator
                    if (task.hasCodeSnippet) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = "Has Code",
                            tint = Color(0xFF3B82F6),
                            modifier = Modifier.size(Spacing.l)
                        )
                    }
                }
                // Status
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountTree,
                        contentDescription = "Status",
                        tint = task.status.color,
                        modifier = Modifier.size(Spacing.l)
                    )
                    Text(
                        text = stringResource(task.status.resourceId) ,
                        color = task.status.color,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FilterItem(
    isSelected: Boolean,
    label: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(interactionSource = null) {
                onClick(label)
            }
            .background(
                shape = RoundedCornerShape(Spacing.m),
                color = if (isSelected) Color(0xFF2563eb) else Color(0xFF1f2937)
            )
            .padding(Spacing.xs)
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xFFd1d5db)
        )
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
            MockTaskRepositoryImpl().getTasks()
        )
    }
}