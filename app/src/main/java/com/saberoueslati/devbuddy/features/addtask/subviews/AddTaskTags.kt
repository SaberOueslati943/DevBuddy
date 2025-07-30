package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.TaskTag
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.features.addtask.AddTaskState
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun ColumnScope.AddTaskTags(action: (AddTaskAction) -> Unit, state: AddTaskState) {
    AppText(text = stringResource(R.string.task_tags))
    Filler(height = Spacing.xs)
    val tags = TaskTag.entries
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        tags.forEach { tag ->
            Card(
                modifier = Modifier.Companion,
                onClick = {
                    action.invoke(AddTaskAction.OnTagClicked(tag))
                },
                elevation = CardDefaults.cardElevation(defaultElevation = Spacing.xxs),
                colors = CardDefaults.cardColors(containerColor = if (tag in state.tags) tag.backgroundColor.copy(alpha = 0.3f) else Color(0xFF374151)),
                border = BorderStroke(1.dp, if (tag in state.tags) tag.color.copy(alpha = 0.5f) else Color(0xFF6B7280))
            ) {
                Row(
                    modifier = Modifier.Companion
                        .padding(Spacing.m),
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        text = stringResource(tag.resourceId),
                        color = if (tag in state.tags) tag.color else Color(0xFF9CA3AF)
                    )
                }
            }
        }
    }
}