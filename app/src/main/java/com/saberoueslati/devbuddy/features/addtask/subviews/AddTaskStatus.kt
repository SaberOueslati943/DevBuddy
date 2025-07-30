package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.features.addtask.AddTaskState
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun ColumnScope.AddTaskStatus(action: (AddTaskAction) -> Unit, state: AddTaskState) {
    AppText(text = stringResource(R.string.task_status))
    Filler(height = Spacing.xs)
    val status = TaskStatus.entries.dropLast(1)
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        status.forEach { status ->
            Card(
                modifier = Modifier.Companion
                    .fillMaxWidth(),
                onClick = {
                    action.invoke(AddTaskAction.OnStatusChanged(status))
                },
                elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                colors = CardDefaults.cardColors(containerColor = if (status == state.status) Color(0x4008298D) else Color(0xFF1E293B)),
                border = BorderStroke(1.dp, if (status == state.status) Color(0xFF3B82F6) else Color(0xFF374151))
            ) {
                Row(
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .padding(Spacing.m),
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        text = stringResource(status.resourceId)
                    )
                    Box(
                        modifier = Modifier.Companion
                            .size(Spacing.m)
                            .clip(CircleShape)
                            .background(status.color)
                    )
                }
            }
        }
    }
}