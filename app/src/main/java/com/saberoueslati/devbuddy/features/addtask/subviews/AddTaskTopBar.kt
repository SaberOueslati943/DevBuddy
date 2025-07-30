package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.ui.theme.onPrimary
import com.saberoueslati.devbuddy.ui.theme.primary

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddTaskTopBar(action: (AddTaskAction) -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Companion.primary
        ),
        title = {
            Text(
                text = stringResource(R.string.dev_tasks),
                color = Color.Companion.onPrimary
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    action.invoke(AddTaskAction.OnBackClicked)
                }
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button")
            }
        },
        actions = {
            Button(
                onClick = { action.invoke(AddTaskAction.OnSaveTaskClicked) },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                content = { Text(stringResource(R.string.save_task)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
            )
        }
    )
}