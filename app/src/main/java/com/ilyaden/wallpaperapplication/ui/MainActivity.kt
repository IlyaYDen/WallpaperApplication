package com.ilyaden.wallpaperapplication.ui

import android.app.WallpaperManager
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import com.ilyaden.wallpaperapplication.R
import com.ilyaden.wallpaperapplication.ui.theme.WallpaperApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MainActivity : ComponentActivity() {

    lateinit var connectivityManager : ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(application as WallPaperApp).mainActivity = this

        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val width = metrics.bounds.width()
        val height = metrics.bounds.height()

        setContent {
            WallpaperApplicationTheme {
                //screen1(this)
                Navigation(this)
            }
        }
    }
}



@Composable
fun screen1(mainActivity: MainActivity) {
    // A surface container using the 'background' color from the theme


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.wp1),
            contentDescription = "",
            modifier = Modifier
                .size(400.dp)
                .clickable {
                    MainScope().launch(Dispatchers.IO) {

                        setWallpaper(mainActivity, "https://cdn2.thecatapi.com/images/oe.jpg")
                    }
                }
        )
        Image(painter = painterResource(id = R.drawable.wp2),
            contentDescription = "",
            modifier = Modifier
                .size(400.dp)
                .clickable {
                    MainScope().launch(Dispatchers.IO) {

                        setWallpaper(mainActivity, "https://cdn2.thecatapi.com/images/ob.jpg")
                    }
                }
        )
    }
}


private suspend fun setWallpaper(mainActivity: MainActivity, lnk : String) {
    withContext(Dispatchers.IO) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(mainActivity)

            // Download the image from the URL
            val inputStream = URL(lnk).openStream()

            // Set the image as wallpaper
            wallpaperManager.setStream(inputStream)
            //wallpaperManager.setResource(R.drawable.wp1)

            // Close the input stream
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}