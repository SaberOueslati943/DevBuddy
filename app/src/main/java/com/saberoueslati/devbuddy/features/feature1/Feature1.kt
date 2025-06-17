package com.saberoueslati.devbuddy.features.feature1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun Feature1(
    onButtonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text("Feature1")
        Button(onClick = {
            onButtonClick(Random.nextInt())
        }) {
            Text("Navigate to Feature2")
        }
    }
}