package com.ilyaden.wallpaperapplication.ui

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ilyaden.wallpaperapplication.ui.theme.WallpaperApplicationTheme


class MainActivity : ComponentActivity() {

    lateinit var connectivityManager : ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = this;

        setContent {

            val theme = remember { mutableStateOf(false) }

            WallpaperApplicationTheme(theme) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {

                    //screen1(this)
                    Navigation(activity, theme)
                }
            }
        }
    }
}


