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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    AppText(text = stringResource(R.string.task_due_date))

                    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
                    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    var showDatePicker by remember { mutableStateOf(false) }

                    val datePickerState = rememberDatePickerState(
                        initialSelectedDateMillis = selectedDate?.atStartOfDay(ZoneId.systemDefault())
                            ?.toInstant()
                            ?.toEpochMilli()
                    )

                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        val localDate = Instant.ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                        selectedDate = localDate
                                    }
                                    showDatePicker = false
                                }) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDatePicker = false }) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    AppTextField(
                        placeholder = stringResource(R.string.task_due_date_placeholder),
                        value = selectedDate?.format(dateFormatter) ?: "",
                        onValueChange = {},
                        readOnly = true,
                        type = AppTextFieldType.Custom(
                            trailingIcon = {
                                IconButton(onClick = { showDatePicker = true }) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Pick Date",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    AppText(text = stringResource(R.string.task_estinamte))
                    AppTextField(
                        placeholder = "",
                        value = "0", // TODO:
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            // TODO:
                        },
                        textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                        type = AppTextFieldType.Custom(
                            leadingIcon = {
                                IconButton(onClick = {
                                    // TODO:
                                }) {
                                    Icon(
                                        modifier = Modifier.padding(Spacing.xxs),
                                        imageVector = Icons.Default.Remove, tint = Color.White, contentDescription = "minus one"
                                    )
                                }

                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    // TODO:
                                }) {
                                    Icon(
                                        modifier = Modifier.padding(Spacing.xxs),
                                        imageVector = Icons.Default.Add, tint = Color.White, contentDescription = "plus one"
                                    )
                                }
                            }
                        )
                    )
                }
            }

            Filler(height = Spacing.xxl)
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