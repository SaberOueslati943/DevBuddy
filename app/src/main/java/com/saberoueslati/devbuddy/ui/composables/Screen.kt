package com.saberoueslati.devbuddy.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

object Screen {

    @Composable
    fun widthInPx(): Int {
        val windowInfo = LocalWindowInfo.current
        return windowInfo.containerSize.width
    }

    @Composable
    fun heightInPx(): Int {
        val windowInfo = LocalWindowInfo.current
        return windowInfo.containerSize.height
    }

    @Composable
    fun widthInDp(): Dp {
        val density = LocalDensity.current
        return with(density) { widthInPx().toDp() }
    }

    @Composable
    fun heightInDp(): Dp {
        val density = LocalDensity.current
        return with(density) { heightInPx().toDp() }
    }

    @Composable
    fun widthPercent(percent: Float): Dp {
        require(percent in 0f..1f) { "percent must be between 0f and 1f" }
        val density = LocalDensity.current
        return with(density) { (widthInPx() * percent).toDp() }
    }

    @Composable
    fun heightPercent(percent: Float): Dp {
        require(percent in 0f..1f) { "percent must be between 0f and 1f" }
        val density = LocalDensity.current
        return with(density) { (heightInPx() * percent).toDp() }
    }
}

