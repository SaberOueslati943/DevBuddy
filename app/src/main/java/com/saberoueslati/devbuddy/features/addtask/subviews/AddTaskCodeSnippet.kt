package com.saberoueslati.devbuddy.features.addtask.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.features.addtask.AddTaskAction
import com.saberoueslati.devbuddy.features.addtask.AddTaskState
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.composables.Screen

@Composable
fun AddTaskCodeSnippet(action: (AddTaskAction) -> Unit, state: AddTaskState) {
    Column {
        Row(
            modifier = Modifier.Companion.fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(text = stringResource(R.string.task_code_snippet))
            TextButton(onClick = {
                action.invoke(AddTaskAction.OnCodeSnippetClicked)
            }) {
                Row(
                    verticalAlignment = Alignment.Companion.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Code, contentDescription = "Code Snippet", tint = Color(0xFF2563EB))
                    AppText(text = stringResource(if (state.showCodeSnippet) R.string.task_hide_code_snippet else R.string.task_add_code_snippet), color = Color(0xFF2563EB))
                }
            }
        }
        if (state.showCodeSnippet) {
            AppTextField(
                modifier = Modifier.Companion.height(Screen.heightPercent(0.25f)),
                placeholder = stringResource(R.string.task_description_placeholder),
                value = state.codeSnippet,
                onValueChange = { newValue ->
                    action.invoke(AddTaskAction.OnCodeSnippetChanged(newValue))
                },
                type = AppTextFieldType.Regular
            )
        }
    }
}