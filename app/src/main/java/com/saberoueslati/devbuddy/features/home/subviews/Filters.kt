package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.TaskStatus
import com.saberoueslati.devbuddy.features.home.HomeAction
import com.saberoueslati.devbuddy.features.home.HomeState
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun Filters(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    LazyRow(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(Spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        item {
            FilterItem(
                isSelected = state.filter == null,
                label = stringResource(R.string.filter_all)
            ) {
                onAction.invoke(HomeAction.OnFilterSelected(null))
            }
        }

        items(TaskStatus.entries) { status ->
            if (status != TaskStatus.COMPLETED) {
                FilterItem(
                    isSelected = state.filter == status,
                    label = stringResource(status.resourceId)
                ) {
                    onAction.invoke(HomeAction.OnFilterSelected(status))
                }
            }
        }
    }
}