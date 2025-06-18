package com.saberoueslati.devbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.saberoueslati.devbuddy.features.feature1.Feature1
import com.saberoueslati.devbuddy.features.feature1.Feature1Route
import com.saberoueslati.devbuddy.features.feature2.Feature2
import com.saberoueslati.devbuddy.features.feature2.Feature2Route
import com.saberoueslati.devbuddy.features.home.Home
import com.saberoueslati.devbuddy.features.home.HomeRoute

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(HomeRoute)
    NavDisplay(
        modifier = modifier,
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
                        Home()
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}