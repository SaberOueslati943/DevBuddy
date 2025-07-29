package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun TotalStatusOfTasks(tasks: List<Task>) {
    Row(
        modifier = Modifier.Companion
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