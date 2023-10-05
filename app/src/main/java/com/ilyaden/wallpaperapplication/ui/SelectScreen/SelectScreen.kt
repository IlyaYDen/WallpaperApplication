package com.ilyaden.wallpaperapplication.ui.SelectScreen

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ilyaden.wallpaperapplication.R
import com.ilyaden.wallpaperapplication.di.DIContainer
import com.ilyaden.wallpaperapplication.ui.ImagesScreen.ImagesViewModel
import com.ilyaden.wallpaperapplication.ui.MainActivity
import java.net.URL

@Composable
fun SelectScreen(url: Int, mainActivity: MainActivity) {

    val viewModel = DIContainer.resolveDependency(ImagesViewModel::class.java) //viewModel<ImagesViewModel>()

    val images = remember { viewModel.images}

    val h = LocalConfiguration.current.screenHeightDp.dp.value.toInt()
    val w = LocalConfiguration.current.screenWidthDp.dp.value.toInt()

    Box() {
    AsyncImage(// it is too difficult to load image without libs
        model = images.get(url).raw,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = {

                viewModel.setWallpaper(mainActivity, images.get(url).raw, WallpaperManager.FLAG_SYSTEM,w,h)

            }) {
                Text(text = "setup wallpaper")
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                viewModel.setWallpaper(mainActivity, images.get(url).raw, WallpaperManager.FLAG_LOCK,w,h)
            }) {
                Text(text = "setup lockscreen")
            }
        }
    }
}
@Preview
@Composable
fun SelectedScreenPreview() {

    Box() {
        Image(
            painter = painterResource(id = R.drawable.wp2),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = {
            }) {
                Text(text = "setup wallpaper")
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "setup lockscreen")
            }
        }
    }
}

