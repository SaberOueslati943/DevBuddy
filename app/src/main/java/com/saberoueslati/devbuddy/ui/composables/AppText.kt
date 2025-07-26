package com.saberoueslati.devbuddy.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    type: AppTextType = AppTextType.Regular
) {
    Text(
        modifier = modifier,
        text = text,
        color = color
    )
}

sealed class AppTextType {
    data object Regular : AppTextType()
}