package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.features.addtask.AddTaskState
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.Spacing
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddTaskTiming(action: (AddTaskAction) -> Unit, state: AddTaskState) {
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        Column(
            modifier = Modifier.Companion.weight(1f)
        ) {
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
                            Text(stringResource(android.R.string.ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text(stringResource(android.R.string.cancel))
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            AppText(text = stringResource(com.saberoueslati.devbuddy.R.string.task_due_date))
            Filler(height = Spacing.xs)
            AppTextField(
                placeholder = stringResource(com.saberoueslati.devbuddy.R.string.task_due_date_placeholder),
                value = selectedDate?.format(dateFormatter) ?: "",
                onValueChange = {
                    action.invoke(AddTaskAction.OnDueDateChanged(selectedDate))
                },
                readOnly = true,
                type = AppTextFieldType.Custom(
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Pick Date",
                                tint = Color.Companion.White
                            )
                        }
                    }
                )
            )
        }
        Column(
            modifier = Modifier.Companion.weight(1f)
        ) {
            AppText(text = stringResource(com.saberoueslati.devbuddy.R.string.task_estimate))
            Filler(height = Spacing.xs)
            AppTextField(
                placeholder = "",
                value = state.estimate.toString(),
                keyboardOptions = KeyboardOptions.Companion.Default.copy(keyboardType = KeyboardType.Companion.Number),
                onValueChange = { newValue ->
                    action.invoke(AddTaskAction.OnEstimateChanged(newValue.toInt()))
                },
                textStyle = TextStyle.Companion.Default.copy(textAlign = TextAlign.Companion.Center),
                type = AppTextFieldType.Custom(
                    leadingIcon = {
                        IconButton(onClick = {
                            action.invoke(AddTaskAction.OnEstimateChanged(state.estimate - 1))
                        }) {
                            Icon(
                                modifier = Modifier.Companion.padding(Spacing.xxs),
                                imageVector = Icons.Default.Remove, tint = Color.Companion.White, contentDescription = "minus one"
                            )
                        }

                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            action.invoke(AddTaskAction.OnEstimateChanged(state.estimate + 1))
                        }) {
                            Icon(
                                modifier = Modifier.Companion.padding(Spacing.xxs),
                                imageVector = Icons.Default.Add, tint = Color.Companion.White, contentDescription = "plus one"
                            )
                        }
                    }
                )
            )
        }
    }
}