package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.data.repository.mockTasks
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.features.home.HomeAction
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.Spacing
import com.saberoueslati.devbuddy.ui.theme.background
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

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
                        .padding(top = Spacing.xs)
                        .size(Spacing.m)
                        .clip(CircleShape)
                        .background(task.priority.color)
                )

                Filler(Spacing.m)

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = task.title,
                            color = if (task.status == TaskStatus.COMPLETED) Color(0xFF6B7280) else Color.White,
                            fontSize = 16.sp,
                            textDecoration = if (task.status == TaskStatus.COMPLETED) TextDecoration.LineThrough else TextDecoration.None,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                    Filler(Spacing.xxs)

                    Text(
                        text = task.description,
                        color = Color(0xFF9CA3AF),
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }


                var expanded by remember { mutableStateOf(false) }
                var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

                Box {
                    IconButton(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color(0xFF9CA3AF)
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        properties = PopupProperties(focusable = true),
                        containerColor = Color.background
                    ) {
                        TaskStatus.entries.filter { it != task.status }.forEach { status ->
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(Spacing.m)
                                                .clip(CircleShape)
                                                .background(status.color)
                                        )
                                        AppText(text = stringResource(status.resourceId))
                                    }
                                },
                                onClick = {
                                    expanded = false
                                    onAction.invoke(HomeAction.OnTaskStatusClicked(task,status))
                                }
                            )
                        }

                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(Spacing.xxs)
                                ) {
                                    Icon(Icons.Outlined.Delete, contentDescription = "Delete", tint = Color.Red)
                                    AppText(text = stringResource(R.string.task_delete), color = Color.Red)
                                }
                            },
                            onClick = {
                                expanded = false
                                showDeleteConfirmationDialog = true
                            }
                        )
                    }
                }

                if (showDeleteConfirmationDialog) {
                    AlertDialog(
                        onDismissRequest = { showDeleteConfirmationDialog = false },
                        title = {
                            Text(text = stringResource(R.string.task_delete_title))
                        },
                        text = {
                            Text(text = stringResource(R.string.task_delete_text))
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                onAction.invoke(HomeAction.OnDeleteTaskClicked(task))
                                showDeleteConfirmationDialog = false
                                expanded = false
                            }) {
                                Text(stringResource(R.string.task_delete), color = MaterialTheme.colorScheme.error)
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDeleteConfirmationDialog = false
                                expanded = false
                            }) {
                                Text(stringResource(android.R.string.cancel))
                            }
                        }
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
                            text = DateTimeFormatter.ofPattern("MMM dd")
                                .format(task.dueDate), color = Color(0xFF9CA3AF), fontSize = 12.sp
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
                    if (task.codeSnippet.isNotEmpty()) {
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
                        text = stringResource(task.status.resourceId),
                        color = task.status.color,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    DevBuddyTheme {
        TaskItem(mockTasks.first()) { }
    }
}