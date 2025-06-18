package com.saberoueslati.devbuddy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Color.Companion.primary: Color
    @Composable
    get() = MaterialTheme.colorScheme.primary

val Color.Companion.secondary: Color
    @Composable
    get() = MaterialTheme.colorScheme.secondary

val Color.Companion.tertiary: Color
    @Composable
    get() = MaterialTheme.colorScheme.tertiary

val Color.Companion.background: Color
    @Composable
    get() = MaterialTheme.colorScheme.background

val Color.Companion.surface: Color
    @Composable
    get() = MaterialTheme.colorScheme.surface

val Color.Companion.onPrimary: Color
    @Composable
    get() = MaterialTheme.colorScheme.onPrimary

val Color.Companion.onSecondary: Color
    @Composable
    get() = MaterialTheme.colorScheme.onSecondary

val Color.Companion.onSurface: Color
    @Composable
    get() = MaterialTheme.colorScheme.onSurface

val Color.Companion.error: Color
    @Composable
    get() = MaterialTheme.colorScheme.error

val Color.Companion.onError: Color
    @Composable
    get() = MaterialTheme.colorScheme.onError
