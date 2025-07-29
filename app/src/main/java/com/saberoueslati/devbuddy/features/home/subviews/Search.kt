package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.features.home.HomeAction
import com.saberoueslati.devbuddy.features.home.HomeState
import com.saberoueslati.devbuddy.ui.composables.AppTextField
import com.saberoueslati.devbuddy.ui.composables.AppTextFieldType
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun Search(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    AppTextField(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(Spacing.m),
        placeholder = stringResource(R.string.search_tasks),
        value = state.query,
        onValueChange = { newValue: String ->
            onAction.invoke(HomeAction.OnSearchQueryChanged(newValue))
        },
        type = AppTextFieldType.Search(
            onClearClicked = {
                onAction.invoke(HomeAction.OnClearSearchQueryClicked)
            }
        )
    )
}