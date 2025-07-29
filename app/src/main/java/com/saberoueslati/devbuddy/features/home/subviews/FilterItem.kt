package com.saberoueslati.devbuddy.features.home.subviews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.saberoueslati.devbuddy.ui.theme.Spacing

@Composable
fun FilterItem(
    isSelected: Boolean,
    label: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(interactionSource = null) {
                onClick(label)
            }
            .background(
                shape = RoundedCornerShape(Spacing.m),
                color = if (isSelected) Color(0xFF2563eb) else Color(0xFF1f2937)
            )
            .padding(Spacing.xs)
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xFFd1d5db)
        )
    }
}