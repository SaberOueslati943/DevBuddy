package com.saberoueslati.devbuddy.features.addtask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.domain.model.TaskTag
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.Spacing
import com.saberoueslati.devbuddy.ui.theme.onPrimary
import com.saberoueslati.devbuddy.ui.theme.primary

@Composable
fun AddTask() {
    AddTaskContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskContent() {
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
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button")
                    }
                },
                actions = {
                    Button(
                        onClick = { /* Handle click */ },
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        content = { Text(stringResource(R.string.save_task)) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
                    )
                }
            )
        }
    ) { innerPadding ->

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = Spacing.m)
                .padding(top = Spacing.m)
        ) {
            AppText(text = stringResource(R.string.task_title))
            AppTextField(
                placeholder = stringResource(R.string.task_title_placeholder),
                value = "", // TODO:
                onValueChange = { newValue ->
                    // TODO:
                },
                type = AppTextFieldType.Regular
            )

            Filler(height = Spacing.m)

            AppText(text = stringResource(R.string.task_description))
            AppTextField(
                modifier = Modifier.fillMaxHeight(0.2f),
                placeholder = stringResource(R.string.task_description_placeholder),
                value = "", // TODO:
                onValueChange = { newValue ->
                    // TODO:
                },
                type = AppTextFieldType.Regular
            )

            Filler(height = Spacing.m)

            AppText(text = stringResource(R.string.task_priority))
            val priorities = Priority.entries
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                priorities.chunked(2).forEach { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { priority ->
                            Card(
                                modifier = Modifier
                                    .weight(1f),
                                elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2937)),
                                border = BorderStroke(1.dp, Color(0xFF374151))
                            ) {
                                Row(
                                    modifier = Modifier.padding(Spacing.m),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(Spacing.m)
                                            .clip(CircleShape)
                                            .background(priority.color)
                                    )
                                    AppText(
                                        text = stringResource(priority.resourceId)
                                    )
                                }
                            }
                        }

                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Filler(height = Spacing.m)

            AppText(text = stringResource(R.string.task_status))
            val status = TaskStatus.entries.dropLast(1)
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                status.forEach { status ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2937)),
                        border = BorderStroke(1.dp, Color(0xFF374151))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.m),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppText(
                                text = stringResource(status.resourceId)
                            )
                            Box(
                                modifier = Modifier
                                    .size(Spacing.m)
                                    .clip(CircleShape)
                                    .background(status.color)
                            )
                        }
                    }
                }
            }

            Filler(height = Spacing.m)

            AppText(text = stringResource(R.string.task_tags))
            val tags = TaskTag.entries
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                tags.forEach { tag ->
                    Card(
                        modifier = Modifier,
                        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                        colors = CardDefaults.cardColors(containerColor = tag.backgroundColor.copy(alpha = 0.3f)),
                        border = BorderStroke(1.dp, tag.color.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(Spacing.m),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppText(
                                text = stringResource(tag.resourceId),
                                color = tag.color
                            )
                        }
                    }
                }
            }

            Filler(height = Spacing.m)

            Row {
                Column {
                    AppText(text = stringResource(R.string.task_due_date))
                    
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun AddTaskPreview() {
    DevBuddyTheme {
        AddTask()
    }
}