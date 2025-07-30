package com.saberoueslati.devbuddy.features.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskCodeSnippet
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskPriority
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskStatus
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskTags
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskTiming
import com.saberoueslati.devbuddy.features.addtask.subviews.AddTaskTopBar
import com.saberoueslati.devbuddy.ui.composables.AppText
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.composables.Filler
import com.saberoueslati.devbuddy.ui.composables.Screen
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.Spacing
import com.saberoueslati.devbuddy.utils.React

@Composable
fun AddTask(
    backStack: NavBackStack,
    vm: AddTaskViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    vm.reaction.React {
        when (it) {
            AddTaskReaction.OnBackClicked -> backStack.remove(AddTaskRoute)
            AddTaskReaction.OnSaveTaskCompleted -> {
                // TODO: add boolean to home so we can show a snack bar with success
                backStack.remove(AddTaskRoute)
            }

            AddTaskReaction.InvalidTitle -> snackBarHostState.showSnackbar(
                message = context.getString(R.string.task_title_required),
                duration = SnackbarDuration.Short
            )
        }
    }

    AddTaskContent(
        state = state,
        snackbarHostState = snackBarHostState,
        action = vm::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskContent(
    state: AddTaskState,
    snackbarHostState: SnackbarHostState,
    action: (AddTaskAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AddTaskTopBar(action)
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
            Filler(height = Spacing.xs)
            AppTextField(
                placeholder = stringResource(R.string.task_title_placeholder),
                value = state.title,
                onValueChange = { newValue ->
                    action.invoke(AddTaskAction.OnTitleChanged(newValue))
                },
                type = AppTextFieldType.Regular
            )

            Filler(height = Spacing.xl)

            AppText(text = stringResource(R.string.task_description))
            Filler(height = Spacing.xs)
            AppTextField(
                modifier = Modifier.height(Screen.heightPercent(0.15f)),
                placeholder = stringResource(R.string.task_description_placeholder),
                value = state.description,
                onValueChange = { newValue ->
                    action.invoke(AddTaskAction.OnDescriptionChanged(newValue))
                },
                type = AppTextFieldType.Regular
            )

            Filler(height = Spacing.xl)

            AddTaskPriority(action, state)

            Filler(height = Spacing.xl)

            AddTaskStatus(action, state)

            Filler(height = Spacing.xl)

            AddTaskTags(action, state)

            Filler(height = Spacing.xl)

            AddTaskTiming(action, state)

            Filler(height = Spacing.xl)

            AddTaskCodeSnippet(action, state)

            Filler(height = Spacing.xxl)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "S23 Ultra Preview",
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=412dp,height=933dp,dpi=500"
)
@Composable
private fun AddTaskPreview() {
    DevBuddyTheme {
        AddTaskContent(AddTaskState(), SnackbarHostState()) {}
    }
}