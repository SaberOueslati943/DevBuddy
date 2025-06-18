package com.saberoueslati.devbuddy.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.saberoueslati.devbuddy.R
import com.saberoueslati.devbuddy.domain.model.Task
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.onSecondary
import com.saberoueslati.devbuddy.ui.theme.secondary
import com.saberoueslati.devbuddy.ui.theme.surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    vm: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.surface)
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.secondary
            ),
            title = {
                Row {
                    Text("\uD83C\uDFE0")
                    Text(
                        stringResource(R.string.home),
                        color = Color.onSecondary
                    )
                }
            }
        )

        LazyColumn {
            items(items = vm.tasks) { item ->
                TaskItem(item)
            }
        }
    }
}

@Composable
fun TaskItem(
    item: Task
) {
    Column {
        Text("Title: ${item.name}")
        Text("Title: ${item.description}")
        Text("Title: ${item.done}")
    }
}

@Preview
@PreviewLightDark
@Composable
private fun HomePreview() {
    DevBuddyTheme {
        Home()
    }
}