package com.saberoueslati.devbuddy.navigation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.saberoueslati.devbuddy.features.addtask.AddTask
import com.saberoueslati.devbuddy.features.addtask.AddTaskRoute
import com.saberoueslati.devbuddy.features.home.Home
import com.saberoueslati.devbuddy.features.home.HomeRoute

private const val NAV_ANIMATION_DURATION = 500

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

        transitionSpec = {
            ContentTransform(
                targetContentEnter = slideInHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                ) { fullWidth -> fullWidth } + fadeIn(tween(NAV_ANIMATION_DURATION)),

                initialContentExit = slideOutHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                ) { fullWidth -> -fullWidth } + fadeOut(tween(NAV_ANIMATION_DURATION))
            )
        },

        popTransitionSpec = {
            ContentTransform(
                targetContentEnter = slideInHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                ) { fullWidth -> -fullWidth } + fadeIn(tween(NAV_ANIMATION_DURATION)),

                initialContentExit = slideOutHorizontally(
                    animationSpec = tween(NAV_ANIMATION_DURATION)
                ) { fullWidth -> fullWidth } + fadeOut(tween(NAV_ANIMATION_DURATION))
            )
        },

        entryProvider = { key ->
            when (key) {
                is HomeRoute -> NavEntry(key) {
                    Home(backStack)
                }

                is AddTaskRoute -> NavEntry(key) {
                    AddTask(backStack)
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}
