package com.saberoueslati.devbuddy.overlord

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalOverlordEdict = compositionLocalOf<OverlordEdict> {
    OverlordEdict(topAppBarColor = Color.Red)
}

data class OverlordEdict(
    val showTopAppBar: Boolean = true,
    val topAppBarColor: Color
)