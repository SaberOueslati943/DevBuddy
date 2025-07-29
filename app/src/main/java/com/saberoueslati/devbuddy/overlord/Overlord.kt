package com.saberoueslati.devbuddy.overlord

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.saberoueslati.devbuddy.navigation.NavigationRoot
import com.saberoueslati.devbuddy.ui.theme.DevBuddyTheme
import com.saberoueslati.devbuddy.ui.theme.background
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class Overlord : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            DevBuddyTheme {
                Surface(
                    color = Color.background
                ) {
                    NavigationRoot()
                }
            }
        }
    }
}