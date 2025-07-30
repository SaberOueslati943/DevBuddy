package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.Priority
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.features.addtask.AddTaskState
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun ColumnScope.AddTaskPriority(action: (AddTaskAction) -> Unit, state: AddTaskState) {
    AppText(text = stringResource(R.string.task_priority))
    Filler(height = Spacing.xs)
    val priorities = Priority.entries
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        priorities.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                rowItems.forEach { priority ->
                    Card(
                        modifier = Modifier.Companion
                            .weight(1f),
                        onClick = {
                            action.invoke(AddTaskAction.OnPriorityChanged(priority))
                        },
                        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                        colors = CardDefaults.cardColors(containerColor = if (priority == state.priority) Color(0x4008298D) else Color(0xFF1E293B)),
                        border = BorderStroke(1.dp, if (priority == state.priority) Color(0xFF3B82F6) else Color(0xFF374151))
                    ) {
                        Row(
                            modifier = Modifier.Companion.padding(Spacing.m),
                            verticalAlignment = Alignment.Companion.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                        ) {
                            Box(
                                modifier = Modifier.Companion
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
                    Spacer(modifier = Modifier.Companion.weight(1f))
                }
            }
        }
    }
}