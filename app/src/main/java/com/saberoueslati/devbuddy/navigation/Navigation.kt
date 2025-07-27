package com.saberoueslati.devbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.saberoueslati.devbuddy.features.addtask.AddTask
import com.saberoueslati.devbuddy.features.addtask.AddTaskRoute
import com.saberoueslati.devbuddy.features.feature1.Feature1
import com.saberoueslati.devbuddy.features.feature1.Feature1Route
import com.saberoueslati.devbuddy.features.feature2.Feature2
import com.saberoueslati.devbuddy.features.feature2.Feature2Route
import com.saberoueslati.devbuddy.features.home.Home
import com.saberoueslati.devbuddy.features.home.HomeRoute

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(HomeRoute)
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Feature1Route -> {
                    NavEntry(
                        key = key
                    ) {
                        Feature1 { id ->
                            backStack.add(Feature2Route(id))
                        }
                    }
                }

                is Feature2Route -> {
                    NavEntry(
                        key = key
                    ) {
                        Feature2(key.id)
                    }
                }

                is HomeRoute -> {
                    NavEntry(
                        key = key
                    ) {
                        Home(backStack)
                    }
                }

                is AddTaskRoute -> {
                    NavEntry(
                        key = key
                    ) {
                        AddTask(backStack)
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}