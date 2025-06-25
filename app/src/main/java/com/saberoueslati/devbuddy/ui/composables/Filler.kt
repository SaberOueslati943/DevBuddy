package com.saberoueslati.devbuddy.ui.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Adds a flexible vertical spacer inside a [ColumnScope] using [weight].
 *
 * @param weight The relative weight of the space to occupy.
 */
@Composable
fun ColumnScope.Filler(
    weight: Float = 1f
) {
    Spacer(modifier = Modifier.weight(weight))
}

/**
 * Adds a flexible horizontal spacer inside a [RowScope] using [weight].
 *
 * @param weight The relative weight of the space to occupy.
 */
@Composable
fun RowScope.Filler(
    weight: Float = 1f
) {
    Spacer(modifier = Modifier.weight(weight))
}

/**
 * Adds a fixed-height spacer inside a [ColumnScope].
 *
 * @param height The exact vertical space in [Dp].
 */
@Composable
fun ColumnScope.Filler(
    height: Dp
) {
    Spacer(modifier = Modifier.height(height))
}

/**
 * Adds a fixed-width spacer inside a [RowScope].
 *
 * @param width The exact horizontal space in [Dp].
 */
@Composable
fun RowScope.Filler(
    width: Dp
) {
    Spacer(modifier = Modifier.width(width))
}
