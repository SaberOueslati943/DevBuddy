package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.saberoueslati.devbuddy.features.home.HomeAction

@Composable
fun AddTaskButton(onAction: (HomeAction) -> Unit) {
    Box(
        modifier = Modifier.Companion.fillMaxSize(),
        contentAlignment = Alignment.Companion.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onAction.invoke(HomeAction.OnAddTaskClicked) },
            modifier = Modifier.Companion
                .navigationBarsPadding()
                .padding(16.dp),
            containerColor = Color(0xFF2563EB)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                tint = Color.Companion.White
            )
        }
    }
}