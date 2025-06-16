package com.saberoueslati.devbuddy.features.feature2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Feature2(
    id: Int
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Feature2 with Id: $id")
    }
}